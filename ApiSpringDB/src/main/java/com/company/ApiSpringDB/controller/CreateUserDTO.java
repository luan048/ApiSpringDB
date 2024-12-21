package com.company.ApiSpringDB.controller;

/*Usado para definir o que o usuário deve passar como parametro para criar um user*/
public record CreateUserDTO(String name, String email, String password) {
}
