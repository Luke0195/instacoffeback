> ### Agendamento

> ### Casos de Sucesso.


- [X] Recebe uma requisição do tipo <b> POST </b> /spots/:spotId/appointment.
- [ ] Valida os campos obrigatórios <b> userId</b>, <b>spotId</b>,<b> data</b>.
- [ ] O agendamento do spot começa com reserva não aprovada.
- [ ] Valida se o id do usuário existe.
- [ ] Valida se o id do spot existe.
- [ ] Cria um agendamento com os dados informados corretamente.
- [ ] Retorna <b> 201 </b> com os dados do agendamento criado.

> ### Exceções 

- [ ] Retorna <b> 404</b> se a rota /spots/:spotId/appointment.
- [ ] Retorna <b> 400 </b> se os dados não forem informados corretamento.
- [ ] Retorna <b> 400 </b> se o id do usuário não existir.
- [ ] Retorna <b> 400 </b> se o id do spot não existir.
- [ ] Retorna <b> 500 </b> se ocorrer um erro ao cadastrar um agendamento.