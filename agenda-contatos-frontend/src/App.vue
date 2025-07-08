<script setup>

import MainGrid from './components/MainGrid.vue'
import { onMounted, ref } from 'vue';
import NewContactModal from './components/NewContactModal.vue';
import axios from 'axios';

const modalIsVisible = ref(false);
const contatos = ref([]);
const contatoSelecionado = ref({
  id: null,
  nome: '',
  telefone: '',
  cidade: '',
  estado: '',
  email: '',
  categoria: 'Aluno'
});

const abrirModal = () => {
  modalIsVisible.value = true;
};

const fecharModal = () => {
  modalIsVisible.value = false;
  contatoSelecionado.value = {
    id: null,
    nome: '',
    telefone: '',
    cidade: '',
    estado: '',
    email: '',
    categoria: 'Aluno'
  };
  buscarTodos();
};

const buscarTodos = async () => {
    try {
        const response = await axios.get('http://localhost:8080/contatos');
        contatos.value = response.data;
    } catch (error) {
        console.log(error);
    }
};

const excluirContato = async (contato) => {
    try {
        await axios.delete(`http://localhost:8080/contatos/excluir/${contato.id}`);
        buscarTodos();
    } catch (error) {
        console.log(error);
    }
};

const editarContato = async (contato) => {
  contatoSelecionado.value = { ...contato };
  modalIsVisible.value = true;
};

onMounted(() => {
    buscarTodos();
});

</script>

<template>
  <NewContactModal 
    v-if="modalIsVisible" 
    :contato="contatoSelecionado" 
    :acao="contatoSelecionado.id ? 'editar' : 'novo'"
    @fechar="fecharModal" 
  />

  <div :class="{ 'dark-bg': modalIsVisible }"></div>
  <main>
      <header>
        <h1>Contatos</h1>
      </header>
      <section class="grid-container">
        <div class="button-right-container">
          <button class="primary-button" @click="abrirModal">Adicionar Contato</button>
        </div>
        <MainGrid 
          :contatos="contatos" 
          @excluirContato="excluirContato" 
          @editarContato="editarContato"
        />
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

  header h1 {
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