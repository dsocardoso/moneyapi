package br.com.dsocardoso.moneyapi.repository.filter;

import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;

public class LancamentoFilter {

    private String descricao;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate dataVencimentoDe;
    @DateTimeFormat (pattern = "yyyy-MM-dd")
    private LocalDate dateVencimentoAte;

    public String getDescricao() {
        return descricao;
    }

    public LocalDate getDataVencimentoDe() {
        return dataVencimentoDe;
    }

    public LocalDate getDateVencimentoAte() {
        return dateVencimentoAte;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public void setDataVencimentoDe(LocalDate dataVencimentoDe) {
        this.dataVencimentoDe = dataVencimentoDe;
    }

    public void setDateVencimentoAte(LocalDate dateVencimentoAte) {
        this.dateVencimentoAte = dateVencimentoAte;
    }
}
