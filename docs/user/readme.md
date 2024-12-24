> ### Usuários.



> ### Casos de Sucesso

* [ ] Recebi requisição do tipo  <b> POST </b> na rota /users.
* [ ] Válida os campos obrigatórios <b> name </b> e <b> email </b>.
* [ ] Válida se o campo e-mail é um e-mail válido.
* [ ] Válida se já existe um usuário com um e-mail fornecido.
* [ ] Cria um usuário com os dados informados.
* [ ] Retorna <b> 201 </b> com os dados cadastrado do usuário.

> ### Exceções

* [ ] Retorna 404 se a rota /users não existir.
* [ ] Retorna 400 o campo nome e e-mail não forem informados.
* [ ] Retorna 400 se o campo e-mail já existir.
* [ ] Retorna 500 se ocorrer um erro ao cadastrar um usuário.