package com.example.javafxproject.Tables;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.temporal.TemporalAdjusters;
import java.util.List;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class Funcionario extends Pessoa{
    private double salario_fixo;
    private double pagamentoDoMes;
    private double comissao;
    private double horasDeTrabalhoDeDiferenca;
    private short dia_pagamentoOriginal;
    private short dia_pagamento = dia_pagamentoOriginal;
    private LocalTime intervalo1;
    private LocalTime intervalo2;
    private short duracaoIntervalosMinutos;
    private Cargo cargo;
    private Turno turno;
    private boolean disponivel;
    private List<Compra> compras;
    private List<Venda> vendas;

    public Funcionario(int id, String nome, String email, String contato, String cpf, LocalDate data_nascimento, Endereco endereco, double salario_fixo, short dia_pagamento, short duracaoIntervalosMinutos, LocalTime intervalo1, Cargo cargo, Turno turno){
        super(id, nome, email, contato, cpf, data_nascimento, endereco);
        Verificacoes.verificarParametroNull(salario_fixo, dia_pagamento, duracaoIntervalosMinutos, intervalo1, cargo, turno);
        setSalario_fixo(salario_fixo);
        setDia_pagamentoOriginal(dia_pagamento);
        setDuracaoIntervalosMinutos(duracaoIntervalosMinutos);
        this.intervalo1 = intervalo1;
        setCargo(cargo);
        setTurno(turno);
        disponivel = false;
        comissao = 0;
        horasDeTrabalhoDeDiferenca = 0;

        /*
        executa um metodo todo dia automaticamente, no caso o ajustarDia_pagamento para caso houver
        a necessidade de ajuste do dia do pagamento e caso alguem mude o dia do pagamento original.
        */
        ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(1);
        scheduler.scheduleAtFixedRate(this::ajustarDia_pagamento, 0, 1, TimeUnit.DAYS);

        ScheduledExecutorService scheduler2 = Executors.newScheduledThreadPool(1);
        scheduler2.scheduleAtFixedRate(this::terminarPeriodoDePagamento, 0, 1, TimeUnit.DAYS);
    }

    public Funcionario(String nome, String email, String contato, String cpf, LocalDate data_nascimento, Endereco endereco, double salario_fixo, short dia_pagamento, short duracaoIntervalosMinutos, LocalTime intervalo1, Cargo cargo, Turno turno){
        super(nome, email, contato, cpf, data_nascimento, endereco);
        Verificacoes.verificarParametroNull(salario_fixo, dia_pagamento, duracaoIntervalosMinutos, intervalo1, cargo, turno);
        setSalario_fixo(salario_fixo);
        setDia_pagamentoOriginal(dia_pagamento);
        setDuracaoIntervalosMinutos(duracaoIntervalosMinutos);
        this.intervalo1 = intervalo1;
        setCargo(cargo);
        setTurno(turno);
        disponivel = false;
        comissao = 0;
        horasDeTrabalhoDeDiferenca = 0;

        ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(1);
        scheduler.scheduleAtFixedRate(this::ajustarDia_pagamento, 0, 1, TimeUnit.DAYS);

        ScheduledExecutorService scheduler2 = Executors.newScheduledThreadPool(1);
        scheduler2.scheduleAtFixedRate(this::terminarPeriodoDePagamento, 0, 1, TimeUnit.DAYS);
    }

    public double calcularPagamento(){
        return salario_fixo + (20*(horasDeTrabalhoDeDiferenca)) + comissao;
    }

    public void addCompra(Compra compra){
        compras.add(compra);
    }

    public void addVenda(Venda venda){
        vendas.add(venda);
    }

    public double getSalario_fixo() {
        return salario_fixo;
    }

    public double getComissao() {
        return comissao;
    }

    public double getHorasDeTrabalhoDeDiferenca() {
        return horasDeTrabalhoDeDiferenca;
    }

    public void setSalario_fixo(double salario_fixo) {
        if (salario_fixo < 1320) {
            throw new RuntimeException("Salário insuficiente.");
        }
        this.salario_fixo = salario_fixo;
    }

    public void setComissao(double comissao) {
        if (comissao < 0) {
            throw new RuntimeException("Somente valores positivos para adicionar ao valor da comissão que será entregue ao funcionário no dia do pagamento.");
        
        }
        this.comissao += comissao;
    }

    public void ModificarHorasDeTrabalhoDeDiferenca(double horasDeTrabalhoDeDiferenca) {
        this.horasDeTrabalhoDeDiferenca += horasDeTrabalhoDeDiferenca;
    }

    private void terminarPeriodoDePagamento(){
        if (LocalDate.now().getDayOfMonth() == dia_pagamento) {
        pagamentoDoMes = calcularPagamento();
        horasDeTrabalhoDeDiferenca = 0;
        comissao = 0;
        }
    }

    public void setCargo(Cargo cargo) {
        this.cargo = cargo;
    }

    public void setTurno(Turno turno) {
        this.turno = turno;
    }

    public short getDia_pagamentoOriginal() {
        return dia_pagamentoOriginal;
    }

    public void setDia_pagamentoOriginal(short dia_pagamento) {
        if (dia_pagamento < 1 || dia_pagamento > 31) {
            throw new RuntimeException("Esse dia não existe no calendário.");
        }

        this.dia_pagamentoOriginal = dia_pagamento;
    }

    private void ajustarDia_pagamento(){
        if (dia_pagamentoOriginal == 31) {
            // ajusta o dia do pagamento para o ultimo dia do mes
            dia_pagamento = (short) LocalDate.now().with(TemporalAdjusters.lastDayOfMonth()).getDayOfMonth();

        } else if (dia_pagamentoOriginal == 30 || dia_pagamentoOriginal == 29 && LocalDate.now().getMonthValue() == 2) {
            dia_pagamento = 28;

        } else {
            dia_pagamento = dia_pagamentoOriginal;
        
        }
    }

    public boolean getDisponivel() {
        return disponivel;
    }

    public LocalTime getIntervalo1() {
        return intervalo1;
    }

    public LocalTime getIntervalo2() {
        return intervalo2;
    }

    public short getDuracaoIntervalosMinutos() {
        return duracaoIntervalosMinutos;
    }

    public void setDisponivel() {
        disponivel = !disponivel;
    }

    public double getPagamentoDoMes() {
        return pagamentoDoMes;
    }

    public short getDia_pagamento() {
        return dia_pagamento;
    }

    public Turno getTurno() {
        return turno;
    }

    public void setDuracaoIntervalosMinutos(short duracaoIntervalosMinutos) {
        if (duracaoIntervalosMinutos > 60 || duracaoIntervalosMinutos < 30) {
            throw new RuntimeException("O intervalo deve durar entre 30 e 60 minutos.");
        }
        this.duracaoIntervalosMinutos = duracaoIntervalosMinutos;
    }

    private void verificarIntervalo(LocalTime intervalo){
        LocalTime fimIntervalo = intervalo.plusMinutes(duracaoIntervalosMinutos);
        /*
        Os LocalTimes abaixo foram criados para facilitar a verificação em que um intervalo 
        deve comecar no minimo 2 horas depois do inicio do turno e
        deve terminar no minimo 2 horas antes do fim do turno.
        */
        LocalTime minimoInicioIntervalo = turno.getInicio().plusMinutes(120);
        LocalTime maximoFimIntervalo = turno.getFim().plusMinutes((22*60));

        if (turno.getFim().isBefore(turno.getInicio())) {
            if (fimIntervalo.isAfter(turno.getFim()) && intervalo.isBefore(turno.getInicio())) {
                throw new RuntimeException("O intervalo deve estar dentro do turno do funcionário.");
            }
        } else {
            if (intervalo.isBefore(turno.getInicio()) || fimIntervalo.isAfter(turno.getFim())) {
                throw new RuntimeException("O intervalo deve estar dentro do turno do funcionário.");                
            }
        }

        if (turno.getInicio().isBefore(LocalTime.of(22, 00))) {
            if (intervalo.isBefore(minimoInicioIntervalo) && intervalo.isAfter(turno.getInicio())) {
                throw new RuntimeException("O intervalo deve comecar no mínimo duas horas depois do inicio do turno.");
            }
        } else {
            if (intervalo.isAfter(turno.getInicio()) || intervalo.isBefore(minimoInicioIntervalo)) {
                throw new RuntimeException("O intervalo deve comecar no mínimo duas horas depois do inicio do turno.");
            }
        }

        if (turno.getFim().isAfter(LocalTime.of(2, 00))) {
            if (fimIntervalo.isAfter(maximoFimIntervalo) && intervalo.isBefore(turno.getFim())) {
                throw new RuntimeException("O intervalo deve terminar no mínimo duas horas antes do inicio do turno.");
            }
        } else {
            if (fimIntervalo.isBefore(turno.getFim()) || fimIntervalo.isAfter(maximoFimIntervalo)){
                throw new RuntimeException("O intervalo deve terminar no mínimo duas horas antes do inicio do turno.");
            }
        }
    }

    public void adicionarIntervalo(LocalTime intervalo, Turno turno){
        if (intervalo == null) {
            return;
        }
        if (turno.getDuracaoMinutos() < 7*60 && intervalo1 != null) {
            throw new RuntimeException("Um funcionário com um turno com menos de 7 horas de duração não pode ter mais de um intervalo.");
        }

        if (intervalo2 == null) {
            LocalTime fimIntervalo1 = intervalo1.plusMinutes(duracaoIntervalosMinutos);
            LocalTime fimSegundoIntervalo = intervalo.plusMinutes(duracaoIntervalosMinutos);

            if (fimIntervalo1.isBefore(intervalo1)) {
                if (!intervalo.isBefore(intervalo1) || !intervalo.isAfter(fimIntervalo1)) {
                    throw new RuntimeException("Um intervalo não pode estar dentro de outro.");
                } 

                if (!fimSegundoIntervalo.isBefore(intervalo1) || !fimSegundoIntervalo.isAfter(fimIntervalo1)) {
                    throw new RuntimeException("Um intervalo não pode estar dentro de outro.");
                }

            } else{
                if (!intervalo.isBefore(intervalo1) && !intervalo.isAfter(fimIntervalo1)) {
                    throw new RuntimeException("Um intervalo não pode estar dentro de outro.");
                }
                
                if (!fimSegundoIntervalo.isBefore(intervalo1) && !fimSegundoIntervalo.isAfter(fimIntervalo1)) {
                    throw new RuntimeException("Um intervalo não pode estar dentro de outro.");
                }
            }

            if (fimIntervalo1.isBefore(LocalTime.of(22, 00))) {
                if (intervalo.isBefore(fimIntervalo1.plusMinutes(120)) && intervalo.isAfter(intervalo1)) {
                    throw new RuntimeException("Um segundo intervalo deve ter pelo menos duas horas de diferença do outro.");
                }
            } else {
                if (intervalo.isAfter(fimIntervalo1) || intervalo.isBefore(fimIntervalo1.plusMinutes(duracaoIntervalosMinutos))) {
                    throw new RuntimeException("Um segundo intervalo deve ter pelo menos duas horas de diferença do outro.");
                }
            }

            if (!intervalo1.isBefore(LocalTime.of(2, 00))) {
                if (fimSegundoIntervalo.isBefore(intervalo1) && fimSegundoIntervalo.isAfter(intervalo1.plusHours(22))) {
                    throw new RuntimeException("Um segundo intervalo deve ter pelo menos duas horas de diferença do outro.");
                }
            } else {
                if (fimSegundoIntervalo.isBefore(intervalo1) || fimSegundoIntervalo.isAfter(intervalo1.plusHours(22))){
                    throw new RuntimeException("Um segundo intervalo deve ter pelo menos duas horas de diferença do outro.");
                }
            }
        }

        if (intervalo1 == null) {
            intervalo1 = intervalo;

        } else if (intervalo2 == null) {
            intervalo2 = intervalo;

        } else { 
            throw new RuntimeException("O funcionário já possui dois intervalos, remova um para adicionar outro.");
        }
    }

    public void deletarIntervalo(LocalTime intervalo, LocalTime novoIntervalo){
        if (intervalo2 == null) {
            if (intervalo != intervalo1) {
                throw new RuntimeException("O funcionário não possui um intervalo que se inicie nesse horário.");
            }

            if (novoIntervalo == null) {
                throw new RuntimeException("Um funcionário deve manter pelo menos um intervalo. Caso queira remover esse intervalo, coloque um novo");
            } else {
                verificarIntervalo(novoIntervalo);
                intervalo1 = null;
                adicionarIntervalo(novoIntervalo, turno);
            } 

        } else {
            if (novoIntervalo == null) {
                if (intervalo1 == intervalo) {
                    intervalo1 = intervalo2;
                    intervalo2 = null;

                } else if (intervalo2 == intervalo) {
                    intervalo2 = null;

                } else {
                    throw new RuntimeException("O funcionário não possui um intervalo que se inicie nesse horário.");
                }
       
            } else {
                verificarIntervalo(novoIntervalo);

                if (intervalo1 == intervalo) {
                    intervalo1 = intervalo2;
                    intervalo2 = null;
                    adicionarIntervalo(novoIntervalo, turno);

                } else if (intervalo2 == intervalo) {
                    intervalo2 = null;
                    adicionarIntervalo(novoIntervalo, turno);

                } else {
                    throw new RuntimeException("O funcionário não possui um intervalo que se inicie nesse horário.");

                }
            }   
        }
    }

    public Cargo getCargo() {
        return cargo;
    }

    /*
    @Override
    public String toString() {
        return "Funcionario [salario_fixo=" + salario_fixo + ", pagamentoDoMes=" + pagamentoDoMes + ", comissao="
                + comissao + ", horasDeTrabalhoDeDiferenca=" + horasDeTrabalhoDeDiferenca + ", dia_pagamentoOriginal="
                + dia_pagamentoOriginal + ", dia_pagamento=" + dia_pagamento + ", intervalos="
                + Arrays.toString(intervalos) + ", duracaoIntervalosMinutos=" + duracaoIntervalosMinutos + ", cargo="
                + cargo + ", turno=" + turno + ", disponivel=" + disponivel + ", advertencias=" + advertencias
                + ", compras=" + compras + ", vendas=" + vendas + "]";
    }
    */
}
