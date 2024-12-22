package com.company.ApiSpringDB.controller;

/*Somente será passado o name e password para rota de update*/
public record UpUserDTO(String name, String password) {
}
