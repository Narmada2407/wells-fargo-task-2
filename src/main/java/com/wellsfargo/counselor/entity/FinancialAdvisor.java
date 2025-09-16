package com.example.demo.entities;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "financial_advisor")
public class FinancialAdvisor {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "advisor_id")
    private Long advisorId;

    @Column(nullable = false)
    private String name;

    @Column(unique = true)
    private String email;

    @Column
    private String phone;

    @OneToMany(mappedBy = "financialAdvisor", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Client> clients = new ArrayList<>();

    // JPA requires a no-arg constructor
    public FinancialAdvisor() {}

    // All-args constructor (initializes all instance variables)
    public FinancialAdvisor(Long advisorId, String name, String email, String phone, List<Client> clients) {
        this.advisorId = advisorId;
        this.name = name;
        this.email = email;
        this.phone = phone;
        this.clients = clients == null ? new ArrayList<>() : clients;
    }

    // Getter for id (no setter required)
    public Long getAdvisorId() { return advisorId; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }

    public String getPhone() { return phone; }
    public void setPhone(String phone) { this.phone = phone; }

    public List<Client> getClients() { return clients; }
    public void setClients(List<Client> clients) { this.clients = clients; }

    // convenience helpers
    public void addClient(Client client) {
        clients.add(client);
        client.setFinancialAdvisor(this);
    }

    public void removeClient(Client client) {
        clients.remove(client);
        client.setFinancialAdvisor(null);
    }
}
