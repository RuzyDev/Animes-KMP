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
    
    @ObservedObject var viewModel: SolicitacoesState
    
    init() {
        viewModel = SolicitacoesState()
    }
    
    var body: some View {
        NavigationView {
                    VStack {
                        // Título
                        Text("Solicitações")
                            .font(.largeTitle)
                            .padding()

                        // Lista de solicitações
                        
                        List(viewModel.uiState.solicitacoes, id: \.id) { solicitacao in
                            VStack(alignment: .leading) {
                                Text(solicitacao.descricao)
                                    .font(.headline)
                            }
                        }
                        .refreshable {
                            // Chama o refresh quando o usuário puxa para baixo
                            viewModel.refresh()
                        }
                        
                        // Botão de refresh manual
                        Button(action: {
                            viewModel.refresh()
                        }) {
                            Text("Atualizar Solicitações")
                                .padding()
                                .background(Color.blue)
                                .foregroundColor(.white)
                                .cornerRadius(8)
                        }
                        .padding()
                    }
                    .navigationTitle("Solicitações")
                }
    }
}

#Preview {
    HomeScreen()
}
