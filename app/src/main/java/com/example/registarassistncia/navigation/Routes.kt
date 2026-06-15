package com.example.registarassistncia.navigation

object Routes {

    //ROTAS HOME
    const val HOME = "home"

    //ROTAS ASSISTENCIA
    const val ASSISTENCIAS = "assistencias"

    const val NOVA_ASSISTENCIA = "nova_assistencia"

    const val DETALHES_ASSISTENCIA_BASE = "detalhes_assistencia"

    const val DETALHES_ASSISTENCIA = "$DETALHES_ASSISTENCIA_BASE/{assistenciaId}"

    const val EDITAR_ASSISTENCIA_BASE = "editar_assistencia"

    const val EDITAR_ASSISTENCIA = "editar_assistencia/{assistenciaId}"


    //ROTAS CLIENTE
    const val CLIENTES = "clientes"

    const val LISTA_CLIENTES = "lista_clientes"

    const val NOVO_CLIENTE = "novo_cliente"

    const val DETALHES_CLIENTE = "detalhes_cliente/{clienteId}"

    const val DETALHES_CLIENTE_BASE = "detalhes_cliente"

    const val EDITAR_CLIENTE = "editar_cliente/{clienteId}"

    const val EDITAR_CLIENTE_BASE = "editar_cliente"



    //ROTAS EQUIPAMENTO
    const val EQUIPAMENTOS = "equipamentos"

    const val LISTA_EQUIPAMENTO = "lista_equipamento"

    const val NOVO_EQUIPAMENTO = "novo_equipamento"

    const val DETALHES_EQUIPAMENTO = "detalhes_equipamento/{equipamentoId}"

    const val DETALHES_EQUIPAMENTO_BASE = "detalhes_equipamento"

    const val EDITAR_EQUIPAMENTO_BASE = "editar_equipamento"

    const val EDITAR_EQUIPAMENTO = "editar_equipamento/{equipamentoId}"




}