package com.ikshvaku.ProjectHive.modal;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import org.springframework.data.annotation.Id;

public class Issue {
    @Id //Primitive Key
    @GeneratedValue(strategy = GenerationType.AUTO) //Autogenerate id
    private Long id;
}
