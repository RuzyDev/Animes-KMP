//
//  HomeObservable.swift
//  iosApp
//
//  Created by Ruan Matheus De Oliveira Medeiros on 13/09/24.
//  Copyright © 2024 orgName. All rights reserved.
//

import Foundation
import shared

class LoginState: ObservableObject {
    
    let viewModel: LoginViewModel
    
    @Published
    private(set) var uiState: LoginUiState = LoginUiState.companion.Empty
    
    init() {
        viewModel = LoginViewModel()
        viewModel.observeUiState{ value in
            self.uiState = value
        }
    }
    
    deinit {
        viewModel.dispose()
    }
    
    func realizarLogin(idUsuario: String, senha: String){
        let usuario = Int64(idUsuario)!
        viewModel.realizarLogin(idUsuario: usuario, senha: senha)
    }
    
}
