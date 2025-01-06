//
//  DataUtil.swift
//  iosApp
//
//  Created by Ruan  on 06/01/25.
//  Copyright © 2025 orgName. All rights reserved.
//

import Foundation

func getPeriodoDia() -> String {
    let agora = Date()
    let calendario = Calendar.current
    let horaAtual = calendario.component(.hour, from: agora)
    let minutoAtual = calendario.component(.minute, from: agora)

    // Define os intervalos de tempo
    let manhaInicio = 6 * 60 // 6:00
    let tardeInicio = 12 * 60 // 12:00
    let noiteInicio = 18 * 60 // 18:00
    let horaEmMinutos = horaAtual * 60 + minutoAtual

    if horaEmMinutos >= manhaInicio && horaEmMinutos < tardeInicio {
        return "Bom dia"
    } else if horaEmMinutos >= tardeInicio && horaEmMinutos < noiteInicio {
        return "Boa tarde"
    } else {
        return "Boa noite"
    }
}
