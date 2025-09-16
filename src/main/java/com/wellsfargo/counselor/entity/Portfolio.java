package com.example.demo.entities;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "portfolio")
public class Portfolio {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "portfolio_id")
    private Long portfolioId;

    @OneToOne(mappedBy = "portfolio", fetch = FetchType.LAZY)
    private Client client;

    @OneToMany(mappedBy = "portfolio", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Security> securities = new ArrayList<>();

    public Portfolio() {}

    // All-args constructor
    public Portfolio(Long portfolioId, Client client, List<Security> securities) {
        this.portfolioId = portfolioId;
        this.client = client;
        this.securities = securities == null ? new ArrayList<>() : securities;
    }

    // Getter for id
    public Long getPortfolioId() { return portfolioId; }

    public Client getClient() { return client; }
    public void setClient(Client client) { this.client = client; }

    public List<Security> getSecurities() { return securities; }
    public void setSecurities(List<Security> securities) { this.securities = securities; }

    // convenience helpers
    public void addSecurity(Security s) {
        securities.add(s);
        s.setPortfolio(this);
    }

    public void removeSecurity(Security s) {
        securities.remove(s);
        s.setPortfolio(null);
    }
}
