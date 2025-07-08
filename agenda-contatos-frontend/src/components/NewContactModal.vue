<script setup>

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
    if (props.acao === 'novo') {
        criarContato();
    } else {
        atualizarContato();
    }
};

const criarContato = async () => {
    try {
        await axios.post('http://localhost:8080/contatos/novo', {
            nome: nome.value,
            telefone: telefone.value,
            cidade: cidade.value,
            estado: estado.value,
            email: email.value,
            categoria: categoria.value
        });
    } catch (error) {
        console.log(error);
    } finally {
        fecharModal();
    }
};

const atualizarContato = async () => {
    try {
        await axios.put(`http://localhost:8080/contatos/editar/${props.contato.id}`, {
            nome: nome.value,
            telefone: telefone.value,
            cidade: cidade.value,
            estado: estado.value,
            email: email.value,
            categoria: categoria.value
        });
    } catch (error) {
        console.log(error);
    } finally {
        fecharModal();
    }
};

</script>

<template>
    <div class="modal">
        <div class="button-right-container">
            <button class="danger-button" @click="fecharModal">X</button>
        </div>
        <h2>Adicionar Novo Contato</h2>

        <form @submit.prevent="enviarFormulario">
            <label for="nome">Nome:</label>
            <input type="text" id="nome" v-model="nome" required>
        
            <label for="telefone">Telefone:</label>
            <input type="text" id="telefone" v-model="telefone" required>
        
            <label for="cidade">Cidade:</label>
            <input type="text" id="cidade" v-model="cidade" required>
        
            <label for="estado">Estado:</label>
            <input type="text" id="estado" v-model="estado" required>
        
            <label for="email">Email:</label>
            <input type="email" id="email" v-model="email">
        
            <label for="categoria">Categoria:</label>
            <select  id="categoria" v-model="categoria">
                <option v-for="categoria in categorias" :value="categoria">{{ categoria }}</option>
            </select>
        
            <button class="primary-button" type="submit">Salvar Contato</button>
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
    border-radius: 8px;
    box-shadow: 0 2px 10px rgba(0, 0, 0, 0.1);
}

.modal h2 {
    margin-bottom: 1rem;
}

.modal label {
    display: block;
    margin-bottom: 0.5rem;
}

.modal input,
.modal select {
    width: 100%;
    padding: 0.5rem;
    margin-bottom: 1rem;
    border: 1px solid #ccc;
    border-radius: 4px;
}

</style>