<script setup>

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
    <div class="grid-title base-grid">
        <div>Nome</div>
        <div>Telefone</div>
        <div>Cidade</div>
        <div>Estado</div>
        <div>Email</div>
        <div>Categoria</div>
    </div>
    <div
        v-for="contato in props.contatos"
        :key="contato.id"
        class="grid-content base-grid"
    >
        <div>{{ contato.nome }}</div>
        <div>{{ contato.telefone }}</div>
        <div>{{ contato.cidade }}</div>
        <div>{{ contato.estado }}</div>
        <div>{{ contato.email }}</div>
        <div>{{ contato.categoria }}</div>
        <div class="action-buttons">
            <button class="primary-button" @click="editarContato(contato)" >Editar</button>
            <button class="danger-button" @click="excluirContato(contato)">Excluir</button>
        </div>
    </div>
</template>

<style scoped>
.grid-title {
    background-color: #e6e6f1;
    padding: 1rem;
    font-weight: bold;
}

.grid-content {
    background-color: #f9f9fc;
    padding: 1rem;
    border-bottom: 1px solid #ccc;
}

.base-grid {
    display: grid;
    /*grid-template-columns: repeat(6, 1fr);*/
    grid-template-columns: 1fr 1fr 1fr 1fr 1fr 1fr 0.7fr;
    gap: 1rem;
}
</style>
