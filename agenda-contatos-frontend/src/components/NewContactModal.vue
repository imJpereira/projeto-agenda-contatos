<script setup>

import { limparTelefone } from '@/assets/functions';
import axios from 'axios';
import { ref } from 'vue';

const props = defineProps({
    contato: {
        type: Object,
        default: () => ({})
    },
    acao: {
        type: String,
        default: 'novo'
    }
});

const emit = defineEmits(['fechar']);

const categorias = ["Aluno", "Responsável", "Professor", "Funcionário", "Gestor"];

const nome = ref(props.contato.nome || '');
const telefone = ref(props.contato.telefone || '');
const cidade = ref(props.contato.cidade || '');
const estado = ref(props.contato.estado || '');
const email = ref(props.contato.email || '');
const categoria = ref(props.contato.categoria || 'Aluno');

const fecharModal = () => {
    emit('fechar');
};

const enviarFormulario = () => {

    if (validarFormulario() !== '') {
        alert('Verifique as seguintes advertências:\n\n'+validarFormulario());
        return;
    }

    if (props.acao === 'novo') {
        criarContato();
    } 
    if (props.acao === 'editar') {
        atualizarContato();
    }
};

const criarContato = async () => {
    try {
        await axios.post('http://localhost:8080/contatos/novo', {
            nome: nome.value.trim(),
            telefone: limparTelefone(telefone.value),
            cidade: cidade.value.trim(),
            estado: estado.value.toUpperCase(),
            email: email.value.trim(),  
            categoria: categoria.value
        });
    } catch (error) {
        alert('Erro ao criar contato: ' + error.message);
    } finally {
        fecharModal();
    }
};

const atualizarContato = async () => {

    try {
        await axios.put(`http://localhost:8080/contatos/editar/${props.contato.id}`, {
            nome: nome.value.trim(),
            telefone: limparTelefone(telefone.value),
            cidade: cidade.value.trim(),
            estado: estado.value.toUpperCase(),
            email: email.value.trim(),
            categoria: categoria.value
        });
    } catch (error) {
        alert('Erro ao atualizar contato: ' + error.message);
    } finally {
        fecharModal();
    }
};

const validarFormulario = () => {
    let mensagem = '';
    if (!nome.value)  {
        mensagem += 'O campo Nome é obrigatório.\n';
    }
    if (!telefone.value) {
        mensagem += 'O campo Telefone é obrigatório.\n';
    }
    if (!cidade.value) {
        mensagem += 'O campo Cidade é obrigatório.\n';
    }
    if (!estado.value) {
        mensagem += 'O campo Estado é obrigatório.\n';
    }
    if (!email.value) {
        mensagem += 'O campo Email é obrigatório.\n';
    }
    if (!categoria.value) {
        mensagem += 'O campo Categoria é obrigatório.\n';
    }
    return mensagem;
};

</script>

<template>
    <div class="modal">
        <div class="button-right-container">
            <button class="small-button danger-button" @click="fecharModal">X</button>
        </div>
        <h2 v-if="props.acao === 'novo'">Adicionar Novo Contato</h2>
        <h2 v-else>Editar Contato</h2>

        <form @submit.prevent="enviarFormulario">
            <label for="nome">Nome:</label>
            <input type="text" id="nome" v-model="nome" > 
        
            <label for="telefone">Telefone:</label>
            <input type="text" id="telefone" v-model="telefone"/>
        
            <div class="city-uf-container">
                <div>
                    <label for="cidade">Cidade:</label>
                    <input type="text" id="cidade" v-model="cidade">
                </div>
                
                <div>
                    <label for="estado">UF:</label>
                    <input type="text" id="estado" v-model="estado" maxlength="2" pattern="[A-Z]{2}" >
                </div>
            </div>
        
            <label for="email">Email:</label>
            <input type="email" id="email" v-model="email">
        
            <label for="categoria">Categoria:</label>
            <select id="categoria" v-model="categoria">
                <option v-for="categoria in categorias" :value="categoria">{{ categoria }}</option>
            </select>
        
            <button class="base-button primary-button" type="submit">Salvar Contato</button>
        </form>
    </div>
</template>

<style scoped>

    .modal {
        position: fixed;
        top: 50%;
        left: 50%;
        transform: translate(-50%, -50%);
        background-color: white;
        z-index: 1000;
        padding: 4rem;
        width: 45rem;
        border-radius: 8px;
        box-shadow: 0 2px 10px rgba(0, 0, 0, 0.1);
    }

    .modal h2 {
        padding: 4rem 0;
        text-align: center;
    }

    .modal label {
        display: block;
        margin-bottom: 0.5rem;
    }

    #estado {
        text-transform: uppercase;
    }

    .modal input,
    .modal select {
        width: 100%;
        padding: 0.5rem;
        margin-bottom: 1rem;
        border: 1px solid #ccc;
        border-radius: 4px;
    }

    .city-uf-container {
        display: flex;
        gap: 1rem;
    }
    .city-uf-container div {
        flex: 1;
    }
    .city-uf-container div:last-child {
        max-width: 8rem;
    }

</style>