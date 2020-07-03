package br.com.codenation;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

interface MeuTimeInterface {

    void incluirTime(Long id, String nome, LocalDate dataCriacao, String corUniformePrincipal, String corUniformeSecundario) throws Exception;

    void incluirJogador(Long id, Long idTime, String nome, LocalDate dataNascimento, Integer nivelHabilidade, BigDecimal salario) throws Exception;

    void definirCapitao(Long idJogador) throws Exception;

    Long buscarCapitaoDoTime(Long idTime) throws Exception;

    String buscarNomeJogador(Long idJogador) throws Exception;

    String buscarNomeTime(Long idTime) throws Exception;

    Long buscarJogadorMaiorSalario(Long idTime) throws Exception;

    BigDecimal buscarSalarioDoJogador(Long idJogador) throws Exception;

    List<Long> buscarJogadoresDoTime(Long idTime) throws Exception;

    Long buscarMelhorJogadorDoTime(Long idTime) throws Exception;

    Long buscarJogadorMaisVelho(Long idTime);

    List<Long> buscarTimes();

    List<Long> buscarTopJogadores(Integer top);
}
