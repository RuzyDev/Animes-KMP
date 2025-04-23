//
//  HomeObservable.swift
//  iosApp
//
//  Created by Ruan Matheus De Oliveira Medeiros on 13/09/24.
//  Copyright © 2024 orgName. All rights reserved.
//

import Foundation
import shared

class DetalhesSolicitacaoState: ObservableObject {
    
    let viewModel:DetalhesSolicitacaoViewModel

    @Published
    private(set) var uiState: DetalhesSolicitacaoUiState = DetalhesSolicitacaoUiState.companion.Empty
    
    @Published
    private(set) var result: ResultState<SolicitacaoAceite> = ResultState.loading
    
    init(idSolicitacao: String) {
        print("DetalhesSolicitacaoState viveu")
        viewModel = DetalhesSolicitacaoViewModel(idSolicitacao: idSolicitacao )
        
        viewModel.observeUiState{ [weak self] value in
            self?.uiState = value
        }
    }
    
    deinit {
        print("DetalhesSolicitacaoState morreu")
        viewModel.dispose()
    }
    

    func responderSolicitacao(solicitacao: SolicitacaoAceite, resposta: Bool){
        viewModel.responderSolicitacao(solicitacao: solicitacao, reposta: resposta)
    }
            
}
