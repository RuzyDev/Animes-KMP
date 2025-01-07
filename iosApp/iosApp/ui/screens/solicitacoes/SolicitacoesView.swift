//
//  HomeScreen.swift
//  iosApp
//
//  Created by Ruan Matheus De Oliveira Medeiros on 13/09/24.
//  Copyright © 2024 orgName. All rights reserved.
//

import SwiftUI
import shared

struct SolicitacoesView: View {
    
    @Binding var path: NavigationPath
    @StateObject var state: SolicitacoesState
    
    init(path: Binding<NavigationPath>) {
        _path = path
        _state = StateObject(wrappedValue: SolicitacoesState())
    }
    
    var body: some View {
            
        // Lista de solicitações
        VStack{
            if(state.uiState.solicitacoes.isEmpty){
                SemDados(label: "Sem solicitações no momento")
            }else{
                SearchWithFilterView(
                    searchText: $state.search,
                    selectedFilter: $state.filter
                )
                List(state.uiState.solicitacoes, id: \.id) { solicitacao in
                    CardSolicitacaoView(solicitacao: solicitacao,
                    verDetalhes: {id in
                        path.navigate(route: Route.detalhesSolicitacao(id: id))
                    },
                    responder: {aceito in
                        state.responderSolicitacao(solicitacao: solicitacao, resposta: aceito)
                    })
                }
            }
        }.refreshable {
            // Chama o refresh quando o usuário puxa para baixo
            state.refresh()
        }
        .listStyle(.plain)
        .navigationTitle("Solicitações")
    }
}

struct CardSolicitacaoView: View {
    let solicitacao: SolicitacaoAceite
    let verDetalhes: (String) -> Void
    let responder: (Bool) -> Void

    var body: some View {
        HStack(spacing: 4) {
            VStack(alignment: .leading, spacing: 4) {
                Text(solicitacao.tipoSolicitacao.descricao)
                    .font(.title3)
                    .fontWeight(.bold)
                    .foregroundColor(.primary)

                Text(solicitacao.descricao)
                    .font(.subheadline)
                    .fontWeight(.regular)
                    .foregroundColor(.secondary)
                    .lineLimit(2)
                    .truncationMode(.tail)
            }
            .frame(maxWidth: .infinity, alignment: .leading)
            .padding(.trailing, 4)

            // Botão Autorizar
            Text("Autorizar")
                .font(.footnote)
                .fontWeight(.bold)
                .foregroundColor(.white)
                .padding(6)
                .background(Color.green)
                .clipShape(RoundedRectangle(cornerRadius: 8))
                .onTapGesture {
                    responder(true)
                }

            // Botão Negar
            Text("Negar")
                .font(.footnote)
                .fontWeight(.bold)
                .foregroundColor(.white)
                .padding(6)
                .background(Color.red)
                .clipShape(RoundedRectangle(cornerRadius: 8))
                .onTapGesture {
                    responder(false)
                }
        }
        .padding(12)
        .background(Color(.systemGray6))
        .clipShape(RoundedRectangle(cornerRadius: 12))
        .onTapGesture {
            verDetalhes(solicitacao.id)
        }
    }
}


struct SearchWithFilterView: View {
    @State private var showFilters: Bool = false
    @Binding var searchText: String
    @Binding var selectedFilter: TipoSolicitacao

    var body: some View {
        HStack(spacing: 8) {
            // Campo de pesquisa
            TextField("Pesquisar...", text: $searchText)
                .textFieldStyle(RoundedBorderTextFieldStyle())
                .padding(.leading, 8)
                .frame(maxHeight: 40)
                .overlay(
                    HStack {
                        if !searchText.isEmpty {
                            Button(action: {
                                searchText = ""
                            }) {
                                Image(systemName: "xmark.circle.fill")
                                    .foregroundColor(.gray)
                            }
                            .padding(.trailing, 8)
                        }
                    },
                    alignment: .trailing
                )
            
            // Botão de filtros
            Button(action: {
                showFilters.toggle()
            }) {
                Image(systemName: "line.horizontal.3.decrease.circle")
                    .resizable()
                    .scaledToFit()
                    .frame(width: 28, height: 28)
                    .foregroundColor(.blue)
            }
            .sheet(isPresented: $showFilters) {
                FiltersView(
                    selectedFilter: $selectedFilter,
                    selectFilter: { value in
                        selectedFilter = value
                        showFilters = false
                    })
            }
        }
        .padding(.horizontal)
    }
}

struct FiltersView: View {
    @Binding var selectedFilter: TipoSolicitacao
    let selectFilter: (TipoSolicitacao) -> Void
    let availableFilters = TipoSolicitacao.entries
    
    var body: some View {
        NavigationView {
            List(availableFilters, id: \.self) { filter in
                HStack {
                    Text(filter.descricao)
                    Spacer()
                    if selectedFilter == filter {
                        Image(systemName: "checkmark")
                            .foregroundColor(.blue)
                    }
                }
                .contentShape(Rectangle())
                .onTapGesture {
                    selectFilter(filter) // Atualiza o filtro selecionado
                }
            }
            .navigationTitle("Filtros")
        }
    }
}
