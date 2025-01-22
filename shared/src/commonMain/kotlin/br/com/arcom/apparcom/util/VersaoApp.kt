package br.com.arcom.apparcom.util

import br.com.arcom.apparcom.util.ConstantsShared.VERSAO_APP

/**
 * Classe para representar a versão do aplicativo seguindo o padrão "X.Y.Z"; X = Major, Y = minor, Z = Patch level (Hotfix opcional para alguns apps).
 *
 * @author Victor Faria / Ruan Matheus
 * @property major O número de versão principal.
 * @property minor O número de versão secundário.
 * @property patch O número de patch da versão.
 * @property hotfix O número de hotfix da versão.
 */
class VersaoApp(
    val major: Long = 0,
    val minor: Long = 0,
    val patch: Long = 0
) {
    override fun toString(): String {
        return "$major.$minor.$patch"
    }

    override fun equals(other: Any?): Boolean {
        if (other == null || other !is VersaoApp) {
            return false
        }
        if (other == this) return true

        return major == other.major
                &&
               minor == other.minor
                &&
               patch == other.patch
    }

    operator fun compareTo(versaoAtual: VersaoApp): Int {
        return this.toString().compareTo(versaoAtual.toString())
    }

    companion object {
        /**
         * Parseia uma string de versão no formato "major.minor.patch" para criar uma instância de VersaoApp.
         *
         * @param versaoString A string de versão a ser parseada.
         * @return Uma instância de VersaoApp correspondente à string de versão fornecida.
         * @throws IllegalArgumentException Se a string de versão não estiver no formato correto ou conter caracteres não numéricos.
         */
        fun parseVersao(versaoString: String): VersaoApp? {
            val split = versaoString.split(".").mapNotNull(String::toLongOrNull)

            if (split.size < 3) {
                return null
            }

            return VersaoApp(split[0], split[1], split[2])
        }
    }
}

/**
 * Verifica se a versão atual do aplicativo é muito antiga em comparação com a versão fornecida.
 *
 * @param novaVersao A versão mais recente do aplicativo fornecida para comparação. Pode ser nulo.
 * @return true se a versão atual do aplicativo for consideravelmente mais antiga do que a versão fornecida,
 *         caso contrário, retorna false.
 */
fun versaoAtualMuitoAntiga(novaVersao: String?): Boolean {
    val versaoNova = novaVersao?.toVersaoAppOrNull() ?: return false
    val versaoAtual = VERSAO_APP.toVersaoAppOrNull() ?: return false

    val versaoNovaMajorMinor = "${versaoNova.major}.${versaoNova.minor}"
    val versaoAtualMajorMinor = "${versaoAtual.major}.${versaoAtual.minor}"

    return if (versaoAtualMajorMinor == versaoNovaMajorMinor) {
        versaoAtual.patch <= (versaoNova.patch - 2)
    } else {
        versaoNovaMajorMinor > versaoAtualMajorMinor
    }
}

fun String.toVersaoAppOrNull(): VersaoApp? {
    return try {
        VersaoApp.parseVersao(this)
    } catch (e: IllegalArgumentException) {
        null
    }
}

