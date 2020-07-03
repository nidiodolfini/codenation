package br.com.codenation;

import br.com.codenation.exceptions.CapitaoNaoInformadoException;
import br.com.codenation.exceptions.IdentificadorUtilizadoException;
import br.com.codenation.exceptions.JogadorNaoEncontradoException;
import br.com.codenation.exceptions.TimeNaoEncontradoException;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;


public class DesafioMeuTimeApplication implements MeuTimeInterface {
	private List<Time> listaTime;
	public DesafioMeuTimeApplication() {
		listaTime = new ArrayList<>();
	}
	public void incluirTime(Long id, String nome, LocalDate dataCriacao, String corUniformePrincipal, String corUniformeSecundario){
		if (this.getTimeById(id) != null) {
				throw new IdentificadorUtilizadoException();
		}
		listaTime.add(new Time(id, nome, dataCriacao, corUniformePrincipal, corUniformeSecundario));
	}

	public void incluirJogador(Long id, Long idTime, String nome, LocalDate dataNascimento, Integer nivelHabilidade,
							   BigDecimal salario){
		Time time = this.getTimeById(idTime);
		if (this.getJogadorById(id) != null) {
			throw new IdentificadorUtilizadoException();
		}
		if (time == null) {
			throw new TimeNaoEncontradoException();
		}
		time.addJogador(new Jogador(id, idTime, nome, dataNascimento, nivelHabilidade, salario));

	}

	public void definirCapitao(Long idJogador){
		Time time = this.getTimeByIdJogador(idJogador);
		if (time == null) {
			throw new JogadorNaoEncontradoException();
		}
		Jogador jogador = time.getJogador(idJogador);
		time.setCapitao(jogador);
	}

	public Long buscarCapitaoDoTime(Long idTime){
		Time time = this.getTimeById(idTime);
		if (time == null) {
			throw new TimeNaoEncontradoException();
		}
		if (time.getCapitao() == null) {
			throw new CapitaoNaoInformadoException();
		}
		return time.getCapitao().getId();
	}

	public String buscarNomeJogador(Long idJogador){
		Jogador jogador = this.getJogadorById(idJogador);
		if (jogador != null) {
			return jogador.getNome();
		}
		throw new JogadorNaoEncontradoException();
	}

	public String buscarNomeTime(Long idTime) {
		Time time = this.getTimeById(idTime);
		if (time == null) {
			throw new TimeNaoEncontradoException();
		}
		return time.getNome();
	}

	public List<Long> buscarJogadoresDoTime(Long idTime){
		Time time = this.getTimeById(idTime);
		if (time == null) {
			throw new TimeNaoEncontradoException();
		}
		return time.getJogadorList()
				.stream()
				.map(Jogador::getId)
				.sorted()
				.collect(Collectors.toList());
	}

	public Long buscarMelhorJogadorDoTime(Long idTime){
		Time time = this.getTimeById(idTime);
		if (time == null) {
			throw new TimeNaoEncontradoException();
		}
		Jogador jogador = Collections.max(time.getJogadorList(), new Comparator<Jogador>() {
			@Override
			public int compare(Jogador jogador1, Jogador jogador2) {
				return Integer.compare(jogador1.getNivelHabilidade(), jogador2.getNivelHabilidade());
			}
		});
		return jogador.getId();
	}

	public Long buscarJogadorMaisVelho(Long idTime){
		Time time = this.getTimeById(idTime);
		if (time == null) {
			throw new TimeNaoEncontradoException();
		}

		Jogador jogador = Collections.min(time.getJogadorList(), (Jogador jogador1, Jogador jogador2) -> {
			LocalDate dataNascimentoJogador1 = jogador1.getDataNascimento();
			LocalDate dataNascimentoJogador2 = jogador2.getDataNascimento();
			if (dataNascimentoJogador1.isAfter(dataNascimentoJogador2)) {
				return 1;
			} else if (dataNascimentoJogador1.isBefore(dataNascimentoJogador2)) {
				return -1;
			}
			return Long.compare(jogador1.getId(), jogador2.getId());
		});
		return jogador.getId();
	}

	public List<Long> buscarTimes() {
		List<Long> times = new ArrayList<>();
		if (!listaTime.isEmpty()) {
			listaTime.forEach(time -> times.add(time.getIdTime()));
			Collections.sort(times);
		}
		return times;
	}

	public Long buscarJogadorMaiorSalario(Long idTime){
		Time time = this.getTimeById(idTime);
		if (time == null) {
			throw new TimeNaoEncontradoException();
		}
		Jogador jogador = Collections.max(time.getJogadorList(), (Jogador jogador1, Jogador jogador2) -> {
			BigDecimal s1 = jogador1.getSalario();
			BigDecimal s2 = jogador2.getSalario();
			return s1.compareTo(s2) != 0 ? s1.compareTo(s2) : Long.compare(jogador2.getId(), jogador1.getId());
		});
		return jogador.getId();
	}

	public BigDecimal buscarSalarioDoJogador(Long idJogador){
		Jogador jogador = this.getJogadorById(idJogador);
		if (jogador == null) {
			throw new JogadorNaoEncontradoException();
		}
		return jogador.getSalario();
	}

	public List<Long> buscarTopJogadores(Integer top) {
		List<Long> TopJogadores = new ArrayList<>();
		List<Jogador> todosJogadores = new ArrayList<>();
		listaTime.forEach(time -> todosJogadores.addAll(time.getJogadorList()));
		if (!todosJogadores.isEmpty()) {
			Collections.sort(todosJogadores, (Jogador jogador, Jogador jogador1) -> {
				Integer n1 = jogador.getNivelHabilidade();
				Integer n2 = jogador1.getNivelHabilidade();
				return !n1.equals(n2) ? Integer.compare(n2, n1) : Long.compare(jogador.getId(), jogador1.getId());
			});
			if (top > todosJogadores.size())
				top = todosJogadores.size();
			List<Jogador> topJogadores = todosJogadores.subList(0, top);
			topJogadores.forEach(jogador -> TopJogadores.add(jogador.getId()));
		}
		return TopJogadores;
	}

	public String buscarCorCamisaTimeDeFora(Long timeDaCasa, Long timeDeFora){
		final Time timeCasa = this.getTimeById(timeDaCasa);
		final Time timeFora = this.getTimeById(timeDeFora);
		if(timeCasa == null || timeFora == null) {
			throw new TimeNaoEncontradoException();
		}
		if (timeCasa.getCorUniformePrincipal().equals(timeFora.getCorUniformePrincipal())) {
			return timeFora.getCorUniformeSecundario();
		} else {
			return timeFora.getCorUniformePrincipal();
		}
	}

	private Time getTimeById(Long idTime) {
		for (Time time: listaTime){
			if (time.getId().equals(idTime)){
				return time;
			}
		}
		return null;
	}

	private Time getTimeByIdJogador(Long idJogador) {
		for (Time time : listaTime) {
			Jogador jogador = time.getJogador(idJogador);
			if (jogador != null) {
				return time;
			}
		}
		return null;
	}

	private Jogador getJogadorById(Long idJogador) {
		for (Time time : listaTime) {
			Jogador jogador = time.getJogador(idJogador);
			if (jogador != null) {
				return jogador;
			}
		}
		return null;
	}

	private Time getTime(Long idTime){
		Time time = listaTime.get(Math.toIntExact(idTime));
		if(time != null){
			return time;
		}
		else{
			throw new TimeNaoEncontradoException();
		}
	}

}
