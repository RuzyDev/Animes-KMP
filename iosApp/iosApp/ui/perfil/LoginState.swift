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
    
    let viewModel:LoginViewModel
    
    init() {
        viewModel = PerfilViewModel()
    }
    
    deinit {
        viewModel.dispose()
    }
    
    func realizarLogin(idUsuario: Int, senha: String){
        viewModel.realizarLogin(idUsuario, senha)
    }
    
}
