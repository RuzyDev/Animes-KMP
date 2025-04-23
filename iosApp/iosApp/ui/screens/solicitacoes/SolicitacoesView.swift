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
            SearchWithFilterView(
                searchText: $state.search,
                selectedFilter: $state.filter,
                refresh: { tipo in state.refresh(page: 1) }
            )
            if(state.uiState.solicitacoes.isEmpty){
                SemDados(label: "Sem solicitações no momento")
                    .frame(maxWidth: .infinity, maxHeight: .infinity)
                Spacer()
            }else{
                List(state.uiState.solicitacoes, id: \.id) { solicitacao in
                    CardSolicitacaoView(solicitacao: solicitacao,
                    verDetalhes: {id in
                        path.navigate(route: Route.detalhesSolicitacao(id: id))
                    },
                    responder: {aceito in
                        state.responderSolicitacao(solicitacao: solicitacao, resposta: aceito)
                    })
                    .listRowSeparator(.hidden)
                }
                Pagination(currentPage: state.uiState.paginacao.page,
                           totalPages: state.uiState.paginacao.totalPaginas,
                           onPageChange: { page in state.refresh(page: page)}
                )
            }
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
                Text(solicitacao.data.formatBrasil())
                    .font(.caption)
                    .fontWeight(.bold)
                    .foregroundColor(.primary)
                
                Text(solicitacao.tipoSolicitacao.descricao)
                    .font(.headline)
                    .fontWeight(.bold)
                    .foregroundColor(.primary)

                Text(solicitacao.descricao)
                    .font(.footnote)
                    .fontWeight(.regular)
                    .foregroundColor(.secondary)
                    .lineLimit(4)
                    .truncationMode(.tail)
            }
            .frame(maxWidth: .infinity, alignment: .leading)
            .padding(.trailing, 4)

            if(solicitacao.status.value != "aguardando-reposta"){
                var color: Color {
                    switch solicitacao.status.value {
                    case "aprovado":
                       return Color.green
                    case "negado":
                       return Color.red
                    default:
                       return Color.primary
                    }}
                
                Text(solicitacao.status.descricao)
                    .font(.system(size: 14, weight: .bold, design: .default)) // Equivalente ao MaterialTheme.typography.bodySmall com negrito
                    .foregroundColor(color) // Define a cor do texto
                    .padding(6) // Espaçamento interno
                    .background(
                        RoundedRectangle(cornerRadius: 8)
                            .stroke(color, lineWidth: 2) // Borda arredondada com espessura
                    )
            }else{
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
    let refresh: (TipoSolicitacao) -> Void

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
                        refresh(value)
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

struct Pagination: View {
    var currentPage: Int64
    var totalPages: Int64
    var onPageChange: (Int64) -> Void

    private var pagesToShow: [Int64] {
        if(totalPages < 1){
            return [1]
        }
        let list = Array(1...totalPages)
        let filtered = list.filter {
            let remaining = Int64(list.count) - $0
            if remaining >= 5 {
                return $0 >= currentPage
            } else {
                return true
            }
        }
        return Array(filtered.prefix(5))
    }

    var body: some View {
        HStack(spacing: 8) {
            // Botão Voltar
            let canGoBack = currentPage > 1
            Button(action: {
                if canGoBack {
                    onPageChange(currentPage - 1)
                }
            }) {
                Image(systemName: "chevron.left")
                    .foregroundColor(.white)
                    .padding(8)
                    .frame(width: 36, height: 36)
                    .background(Color.blue.opacity(canGoBack ? 1 : 0.3))
                    .clipShape(RoundedRectangle(cornerRadius: 8))
            }
            .disabled(!canGoBack)

            // Botões das páginas
            ForEach(pagesToShow, id: \.self) { page in
                let isSelected = page == currentPage
                Text("\(page)")
                    .frame(width: 36, height: 36)
                    .background(
                        RoundedRectangle(cornerRadius: 8)
                            .stroke(Color.blue.opacity(isSelected ? 1 : 0.3), lineWidth: 2)
                    )
                    .onTapGesture {
                        onPageChange(page)
                    }
            }

            // Botão Avançar
            let canGoNext = currentPage < totalPages
            Button(action: {
                if canGoNext {
                    onPageChange(currentPage + 1)
                }
            }) {
                Image(systemName: "chevron.right")
                    .foregroundColor(.white)
                    .padding(8)
                    .frame(width: 36, height: 36)
                    .background(Color.blue.opacity(canGoNext ? 1 : 0.3))
                    .clipShape(RoundedRectangle(cornerRadius: 8))
            }
            .disabled(!canGoNext)
        }
        .padding(.top, 8)
    }
}
