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
        HomeScreen(navigateToSolicitacoes: {} , uiState: state.uiState)
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
                        navigateToSolicitacoes: navigateToSolicitacoes,
                        qtdSolicitacoesPendentes: uiState.qtdSolicitacoesPendentes
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
                    .font(.headline)
                    .foregroundColor(.blue)
                    .lineLimit(1)
                    .truncationMode(.tail)
                    .fontWeight(.bold)
            }
            Spacer()
            Image("ic_logo")
                .resizable()
                .frame(width: 28, height: 28)
        }
        .padding(.vertical, 12)
        .padding(.horizontal, 16)
    }
}

struct MenuSolicitacoes: View {
    var navigateToSolicitacoes: () -> Void
    var qtdSolicitacoesPendentes: Int64

    var body: some View {
        HStack(spacing: 8) {
            Image("ic_check_illustration")
                .resizable()
                .scaledToFit()
                .frame(height: 98)

            VStack(alignment: .trailing, spacing: 4) {
                Text("Descrição da solicitação")
                    .font(.subheadline)
                    .fontWeight(.semibold)
                    .foregroundColor(.gray)
                    .lineLimit(1)
                    .truncationMode(.tail)

                Text("\(qtdSolicitacoesPendentes) pendente(s)")
                    .font(.title)
                    .fontWeight(.semibold)
                    .foregroundColor(.primary)
                    .lineLimit(1)
                    .truncationMode(.tail)
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
