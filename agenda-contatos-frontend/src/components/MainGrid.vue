<script setup>
import axios from 'axios';
import { onMounted, ref } from 'vue'

const props = defineProps({
    contatos: {
        type: Array,
        required: true
    }
});

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
    grid-template-columns: 1.2fr 0.8fr 0.8fr 0.5fr 1.2fr 0.8fr;
    gap: 1rem;
}
</style>
