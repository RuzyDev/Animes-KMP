//
//  StringUtil.swift
//  iosApp
//
//  Created by Ruan  on 06/01/25.
//  Copyright © 2025 orgName. All rights reserved.
//

extension String {
    // Obtém as primeiras 'qtd' palavras de uma string
    func getQtdPalavras(qtd: Int = 2) -> String {
        let words = self.split(separator: " ").filter { !$0.isEmpty }
        let quantidade = min(words.count, qtd)
        return words.prefix(quantidade).joined(separator: " ")
    }

    // Converte cada palavra para o formato "Capitalized"
    func toNome() -> String {
        return self.split(separator: " ")
            .map { $0.lowercased().capitalized }
            .joined(separator: " ")
    }
}
