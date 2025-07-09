<script setup>
import { formatarTelefone } from '@/assets/functions';


const props = defineProps({
    contatos: {
        type: Array,
        required: true
    }
});

const emit = defineEmits(['editarContato', 'excluirContato']);

const editarContato = (contato) => {
    emit('editarContato', contato);
};

const excluirContato = async (contato) => {
    emit('excluirContato', contato);
};


</script>

<template>
    <div
        v-for="contato in props.contatos"
        :key="contato.id"
        class="grid-content base-grid"
    >
        <div>{{ contato.nome }}</div>
        <div>{{ formatarTelefone(contato.telefone) }}</div>
        <div>{{ contato.cidade }}</div>
        <div>{{ contato.estado }}</div>
        <div>{{ contato.email }}</div>
        <div>{{ contato.categoria }}</div>
        <div class="action-buttons">
            <button class="base-button edit-button" @click="editarContato(contato)" >Editar</button>
            <button class="base-button danger-button" @click="excluirContato(contato)">Excluir</button>
        </div>
    </div>
</template>

<style scoped>

    .grid-content {
        background-color: #f9f9fc;
        padding: 1rem;
        border-bottom: 1px solid #ccc;
    
        &:hover {
            background-color: #f2f3f5;
        }
    }

    .grid-content .action-buttons {
        display: flex;
        gap: 0.5rem;
    }

</style>
