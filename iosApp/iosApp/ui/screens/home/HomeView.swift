//
//  HomeScreen.swift
//  iosApp
//
//  Created by Ruan Matheus De Oliveira Medeiros on 13/09/24.
//  Copyright © 2024 orgName. All rights reserved.
//

import SwiftUI
import shared

struct HomeView: View {
    
    @Binding var path: NavigationPath
    @StateObject var state: HomeState
    
    init(path: Binding<NavigationPath>) {
        _path = path
        _state = StateObject(wrappedValue: HomeState())
    }
    
    var body: some View {
        HomeScreen(navigateToSolicitacoes: { path.navigate(route: Route.solicitacoes) } , uiState: state.uiState)
    }
}


struct HomeScreen: View {
    var navigateToSolicitacoes: () -> Void
    var uiState: HomeUiState

    var body: some View {
        VStack {
            TopBarHome(usuario: uiState.usuario)
            ScrollView {
                VStack(spacing: 8) {
                    MenuSolicitacoes(
                        navigateToSolicitacoes: navigateToSolicitacoes
                    )
                }
                .padding(.top, 16)
                .padding(.bottom, 32)
            }
        }
    }
}

struct TopBarHome: View {
    var usuario: Usuario?

    var body: some View {
        HStack(spacing: 12) {
            VStack(alignment: .leading) {
                Text("Olá, \(getPeriodoDia())")
                    .font(.subheadline)
                    .fontWeight(.semibold)
                    .foregroundColor(.gray)

                Text(usuario?.nome.getQtdPalavras().toNome() ?? "Usuário")
                    .font(.title)
                    .foregroundColor(.blue)
                    .lineLimit(1)
                    .truncationMode(.tail)
                    .fontWeight(.bold)
            }
            Spacer()
            Image("ic_arcom")
                .resizable()
                .aspectRatio(contentMode: .fit)
                .frame(height: 28)
        }
        .padding(.vertical, 12)
        .padding(.horizontal, 16)
    }
}

struct MenuSolicitacoes: View {
    var navigateToSolicitacoes: () -> Void

    var body: some View {
        HStack(spacing: 8) {
            Image("ic_check_illustration")
                .resizable()
                .scaledToFit()
                .frame(height: 98)

            Spacer()
            
            VStack(alignment: .trailing, spacing: 4) {
                Text("Aceite ou recuse solicitações")
                    .font(.subheadline)
                    .fontWeight(.semibold)
                    .foregroundColor(.gray)
                    .lineLimit(1)
                    .truncationMode(.tail)

                Text("Autorizar")
                    .font(.title3)
                    .fontWeight(.semibold)
                    .foregroundColor(.primary)
                    .lineLimit(1)
                
            }
        }
        .frame(maxWidth: .infinity, alignment: .center)
        .padding(12)
        .background(Color.blue.opacity(0.2))
        .cornerRadius(8)
        .onTapGesture {
            navigateToSolicitacoes()
        }
        .padding(.horizontal, 16)
    }
}
