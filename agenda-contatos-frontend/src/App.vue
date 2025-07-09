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
    if (!confirm(`Deseja realmente excluir o contato ${contato.nome}?`)) {
        return;
    }

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
      <div class="main-container">
        <header>
          <h1>Contatos</h1>
        </header>
        <section class="crud-container">
          <div class="button-right-container">
            <button class="base-button primary-button" @click="abrirModal">Adicionar Contato</button>
          </div>

          <div class="grid-title base-grid">
            <div>Nome</div>
            <div>Telefone</div>
            <div>Cidade</div>
            <div>Estado</div>
            <div>Email</div>
            <div>Categoria</div>
            <div>Ações</div>
          </div>

          <div class="grid-container">
            <MainGrid
              :contatos="contatos"
              @excluirContato="excluirContato"
              @editarContato="editarContato"
            />
          </div>

        </section>
    </div>
  </main>
</template>

<style scoped>

  main {
    display: flex;
    justify-content: center;
    align-items: center;
    height: 100vh;
    background-color: #f0f0f5;
    overflow: hidden;
  }

  .crud-container {
    display: flex;
    flex-direction: column;
    flex: 1;
    padding: 0 2rem 2rem 2rem;
    min-height: 0;
  }

  .main-container {
    height: 95vh;
    width: 98vw;
    display: flex;
    flex-direction: column;
    min-height: 0;
    background-color: #f9f9fc;
    border-radius: 8px;
    box-shadow: 0 2px 10px rgba(0, 0, 0, 0.1);
  }

  .grid-container {
    flex: 1;
    overflow-y: auto;
  }

  header {
    padding: 2rem 1.5rem;
  }

  header h1 {
    margin: 0.2rem 0;
    font-weight: 500;
    font-size: 4rem;
    color: #333;
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