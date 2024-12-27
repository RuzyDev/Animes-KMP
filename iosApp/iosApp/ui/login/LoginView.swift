//
//  HomeScreen.swift
//  iosApp
//
//  Created by Ruan Matheus De Oliveira Medeiros on 13/09/24.
//  Copyright © 2024 orgName. All rights reserved.
//

import SwiftUI
import shared

struct LoginView: View {
    
    @Binding var path: NavigationPath
    @StateObject var state: LoginState
    
    @State private var idUsuario: String = ""
    @State private var senha: String = ""
    @State private var ocultarSenha: Bool = true
    
    init(path: Binding<NavigationPath>) {
        _path = path
        _state = StateObject(wrappedValue: LoginState())
    }
    
    var body: some View {
            VStack(spacing: 4) {
                if let message = state.uiState.uiMessage {
                    Text(message.message)
                        .foregroundColor(.red)
                        .padding(.bottom, 16)
                }

                Text("Bem-vindo")
                    .font(.largeTitle)
                    .fontWeight(.bold)
                    .frame(maxWidth: .infinity, alignment: .center)
                    .padding(.horizontal, UIScreen.main.bounds.width * 0.05)

                Text("Entre com seus dados para continuar")
                    .font(.title3)
                    .foregroundColor(.secondary)
                    .frame(maxWidth: .infinity, alignment: .center)
                    .padding(.horizontal, UIScreen.main.bounds.width * 0.05)

                Image(systemName: "exclamationmark.triangle.fill")
                    .resizable()
                    .scaledToFit()
                    .foregroundColor(.blue)
                    .frame(height: 200)
                    .padding(.vertical, 16)

                TextField("Usuário", text: $idUsuario)
                    .keyboardType(.numberPad)
                    .textFieldStyle(RoundedBorderTextFieldStyle())
                    .frame(maxWidth: .infinity)
                    .padding(.horizontal, UIScreen.main.bounds.width * 0.05)

                HStack {
                    if ocultarSenha {
                        SecureField("Senha", text: $senha)
                    } else {
                        TextField("Senha", text: $senha)
                    }
                    Button(action: {
                        ocultarSenha.toggle()
                    }) {
                        Image(systemName: ocultarSenha ? "eye.slash" : "eye")
                            .foregroundColor(.gray)
                    }
                }
                .keyboardType(.numberPad)
                .textFieldStyle(RoundedBorderTextFieldStyle())
                .frame(maxWidth: .infinity)
                .padding(.horizontal, UIScreen.main.bounds.width * 0.05)

                Button(action: {
                    state.realizarLogin(idUsuario: idUsuario, senha: senha)
                }) {
                    Text("Entrar")
                        .frame(maxWidth: .infinity)
                        .padding()
                        .foregroundColor(.white)
                        .background(idUsuario.isEmpty || senha.isEmpty ? Color.gray : Color.blue)
                        .cornerRadius(8)
                }
                .disabled(idUsuario.isEmpty || senha.isEmpty)
                .padding(.horizontal, UIScreen.main.bounds.width * 0.05)
                .padding(.top, 16)
            }
            .padding(.vertical, 24)
            .overlay(
                state.uiState.loadingLogin ? ProgressView().scaleEffect(1.5) : nil
            )
        }
}
