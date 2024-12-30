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
    
    init(path: Binding<NavigationPath>) {
        _path = path
    }
    
    var body: some View {
        VStack{
            TopBarHome(usuarioNome: "Ruan")
            List{
                SolicitacaoCard()
            }
        }
    }
}


struct TopBarHome: View {
    let usuarioNome: String? // Substitua por dados reais
    var periodoDia: String { getPeriodoDia() }

    var body: some View {
        HStack {
            Image(systemName: "logo") // Substitua pelo nome real do asset
                .resizable()
                .aspectRatio(contentMode: .fit)
                .frame(width: 32, height: 32)
                .foregroundColor(Color("onPrimary"))
            
            Spacer()
            
            VStack(alignment: .trailing, spacing: 4) {
                Text("Olá, \(periodoDia)")
                    .font(.system(size: 14, weight: .medium)) // Substitua conforme necessário
                    .foregroundColor(Color("onPrimarySecondary"))
                
                Text(usuarioNome ?? "Usuário")
                    .font(.system(size: 18, weight: .semibold)) // Substitua conforme necessário
                    .foregroundColor(Color("onPrimary"))
                    .lineLimit(1)
                    .truncationMode(.tail)
            }
        }
        .padding(.vertical, 12)
        .padding(.horizontal)
        .background(Color("primary"))
        .frame(maxWidth: .infinity)
    }

    func getPeriodoDia() -> String {
        let hour = Calendar.current.component(.hour, from: Date())
        switch hour {
        case 0..<12:
            return "Bom dia"
        case 12..<18:
            return "Boa tarde"
        default:
            return "Boa noite"
        }
    }
}

struct SolicitacaoCard: View {
    var descricao: String = "Descrição da Solicitação" // Substitua pelo texto correto
    var pendentes: Int = 10 // Substitua pelo valor correto
    var navigateToSolicitacoes: () -> Void = {} // Substitua pela navegação real

    var body: some View {
        HStack(spacing: 8) {
            VStack(alignment: .leading, spacing: 4) {
                Text(descricao)
                    .font(.system(size: 16, weight: .semibold)) // Ajuste conforme o design
                    .foregroundColor(Color("onPrimaryContainer"))
                    .lineLimit(1)
                    .truncationMode(.tail)

                Text("\(pendentes) pendentes") // Substitua por pluralização conforme necessário
                    .font(.system(size: 20, weight: .semibold)) // Ajuste conforme o design
                    .foregroundColor(Color("onPrimaryContainer"))
                    .lineLimit(1)
                    .truncationMode(.tail)
            }
            .frame(maxWidth: .infinity, alignment: .leading)

            Image("check_illustration") // Substitua pelo nome real do asset
                .resizable()
                .aspectRatio(contentMode: .fit)
                .frame(height: 86)
        }
        .padding(12)
        .background(Color("primaryContainer"))
        .clipShape(RoundedRectangle(cornerRadius: 12)) // Substitua por CornerShapeAppArcom equivalente
        .onTapGesture {
            navigateToSolicitacoes()
        }
    }
}
