//
//  HomeScreen.swift
//  iosApp
//
//  Created by Ruan Matheus De Oliveira Medeiros on 13/09/24.
//  Copyright © 2024 orgName. All rights reserved.
//

import SwiftUI
import shared

struct DetalhesSolicitacaoView: View {
    
    @Binding var path: NavigationPath
    @StateObject var states: DetalhesSolicitacaoState
    
    init(path: Binding<NavigationPath>, idSolicitacao: String) {
        self._path = path
        self._states = StateObject(wrappedValue: DetalhesSolicitacaoState(idSolicitacao: idSolicitacao))
    }
    
    var body: some View {
        NavigationStack{
            ScrollView{
                if let solicitacao = states.uiState.solicitacao {
                    InformacoesSolicitacao(solicitacaoAceite: solicitacao, responderSolicitacao: states.responderSolicitacao)
                }
            }
        }.navigationTitle("Detalhes solicitação")
    }
}

struct InformacoesSolicitacao: View {
    let solicitacaoAceite: SolicitacaoAceite
    let responderSolicitacao: (SolicitacaoAceite, Bool) -> Void
    
    var body: some View {
        HStack(spacing: 8) {
            // Coluna principal com as informações
            VStack(alignment: .leading, spacing: 4) {
                Text(solicitacaoAceite.tipoSolicitacao.descricao)
                    .font(.headline)
                    .fontWeight(.bold)
                    .foregroundColor(.primary)
                
                Text(solicitacaoAceite.data.formatBrasil())
                    .font(.subheadline)
                    .foregroundColor(.secondary)
            }
            .frame(maxWidth: .infinity, alignment: .leading)
            
            // Coluna com os botões
            VStack(spacing: 8) {
                Button(action: {
                    responderSolicitacao(solicitacaoAceite, true)
                }) {
                    Text("Autorizar")
                        .font(.body)
                        .fontWeight(.bold)
                        .frame(maxWidth: .infinity)
                        .padding(.vertical, 6)
                        .padding(.horizontal, 16)
                        .background(Color.green)
                        .foregroundColor(.white)
                        .cornerRadius(8)
                }
                
                Button(action: {
                    responderSolicitacao(solicitacaoAceite, false)
                }) {
                    Text("Negar")
                        .font(.body)
                        .fontWeight(.bold)
                        .frame(maxWidth: .infinity)
                        .padding(.vertical, 6)
                        .padding(.horizontal, 16)
                        .background(Color.red)
                        .foregroundColor(.white)
                        .cornerRadius(8)
                }
            }
            .frame(width: 120) // Define a largura dos botões para garantir alinhamento
        }
        .padding()
    }
}
