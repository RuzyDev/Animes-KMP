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
    
    @StateObject var state: SolicitacoesState
    
    init() {
        _state = StateObject(wrappedValue: SolicitacoesState())
    }
    
    var body: some View {
            
        // Lista de solicitações
        List(state.uiState.solicitacoes, id: \.id) { solicitacao in
            CardSolicitacaoView(solicitacao: solicitacao, verDetalhes: {id in }, responder: {aceito in })
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
