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
    
    @StateObject var state: LoginState
    
    @State private var idUsuario: String = ""
    @State private var senha: String = ""
    @State private var ocultarSenha: Bool = true
    
    init() {
        _state = StateObject(wrappedValue: LoginState())
    }
    
    var body: some View {
        ZStack{
        
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
                
                Image("ic_arcom")
                    .resizable()
                    .scaledToFit()
                    .foregroundColor(.blue)
                    .frame(maxHeight: .infinity)
                    .padding(16)
                
                TextField("Usuário", text: $idUsuario)
                    .keyboardType(.numberPad)
                    .textFieldStyle(RoundedBorderTextFieldStyle())
                    .frame(maxWidth: .infinity)
                    .padding(.horizontal, UIScreen.main.bounds.width * 0.05)
                
                HStack {
                    if ocultarSenha {
                        SecureField("Senha", text: $senha)
                            .textFieldStyle(RoundedBorderTextFieldStyle())
                    } else {
                        TextField("Senha", text: $senha)
                            .textFieldStyle(RoundedBorderTextFieldStyle())
                    }
                    
                    Button(action: {
                        ocultarSenha.toggle()
                    }) {
                        Image(systemName: ocultarSenha ? "eye.slash" : "eye")
                            .foregroundColor(.gray)
                    }
                }
                .frame(maxWidth: .infinity)
                .padding(.horizontal, UIScreen.main.bounds.width * 0.05)
                .padding(.top, 8)
                
                Button(action: {
                    withAnimation {
                                            state.realizarLogin(idUsuario: idUsuario, senha: senha)
                                        }
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
            
            if state.uiState.loadingLogin {
                    ProgressView("Carregando...")
                        .frame(maxWidth: .infinity, maxHeight: .infinity, alignment: .center)
                        .background(Color.black.opacity(0.3))
                        .ignoresSafeArea()
                        .transition(.opacity)
            }
        }
        
    }
}
