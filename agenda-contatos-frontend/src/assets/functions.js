export const formatarTelefone = (telefone) => {
  let telefoneFormatado = telefone.replace(/\D/g, ''); 

  if (telefoneFormatado.length <= 10) {
    telefoneFormatado = telefoneFormatado.replace(/(\d{2})(\d{4})(\d{0,4})/, '($1) $2-$3');
  } else {
    telefoneFormatado = telefoneFormatado.replace(/(\d{2})(\d{5})(\d{0,4})/, '($1) $2-$3');
  }

  return telefoneFormatado;
}

export const limparTelefone = (telefone) => {
  return telefone.replace(/\D/g, '');
}
