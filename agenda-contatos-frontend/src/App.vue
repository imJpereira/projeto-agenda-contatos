<script setup>

import MainGrid from './components/MainGrid.vue'
import { onMounted, ref } from 'vue';
import NewContactModal from './components/NewContactModal.vue';
import axios from 'axios';

const modalIsVisible = ref(false);
const contatos = ref([]);

const alternarModal = () => {
  modalIsVisible.value = !modalIsVisible.value;

  buscarTodos();
};

const buscarTodos = async () => {
    try {
        const response = await axios.get('http://localhost:8080/contatos');
        contatos.value = response.data;
    } catch (error) {
        console.log(error);
    }
}

onMounted(() => {
    buscarTodos();
});

</script>

<template>
  <NewContactModal v-if="modalIsVisible" @close="alternarModal" />

  <div :class="{ 'dark-bg': modalIsVisible }"></div>
  <main>
    <header>
      <p>Contatos</p>
    </header>
    <section class="grid-container">
      <div class="button-right-container">
        <button class="primary-button" @click="alternarModal">Adicionar Contato</button>
      </div>
      <MainGrid :contatos="contatos"/>
    </section>
</main>
</template>

<style scoped>

  .grid-container {
    height: 95%;
    margin: 2rem;
  }

  header {
    padding: 2rem 1.5rem;
  }

  header p {
  margin: 0.2rem 0;
  font-weight: 500;
  }

  header p:first-child {
  font-size: 3rem;
  font-weight: 700;
  }
  
  .dark-bg {
    height: 100%;
    width: 100%;
    position: fixed;
    top: 0;
    left: 0;
    background-color: rgba(0, 0, 0, 0.5);
    z-index: 999;
  }

</style>