//
//  HomeObservable.swift
//  iosApp
//
//  Created by Ruan Matheus De Oliveira Medeiros on 13/09/24.
//  Copyright © 2024 orgName. All rights reserved.
//

import Foundation
import shared

class SolicitacoesState: ObservableObject {
    
    let viewModel:SolicitacoesViewModel
    
    @Published var search: String = "" {
            didSet {
                viewModel.setSearch(search: self.search) // Atualiza a pesquisa sempre que o texto muda
            }
        }
    
    @Published var filter: TipoSolicitacao = TipoSolicitacao.todos {
            didSet {
                viewModel.setFiltro(filtro: self.filter)
            }
        }

    @Published
    private(set) var uiState: SolicitacoesUiState = SolicitacoesUiState.companion.Empty
    
    init() {
        viewModel = SolicitacoesViewModel()
        
        viewModel.observeUiState{ value in
            self.uiState = value
        }
    }
    
    deinit {
        viewModel.dispose()
    }
    
    func refresh(page: Int64 = 0, tipo: TipoSolicitacao){
        viewModel.buscarSolicitacoes(page: page, tipoSolicitacao: tipo, callback: {})
    }
    
    func responderSolicitacao(solicitacao: SolicitacaoAceite, resposta: Bool){
        viewModel.responderSolicitacao(solicitacao: solicitacao, reposta: resposta)
    }
            
}
