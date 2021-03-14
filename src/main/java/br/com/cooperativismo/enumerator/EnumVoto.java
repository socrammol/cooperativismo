package br.com.cooperativismo.enumerator;

public enum EnumVoto {
	SIM("Sim"),
    NAO("não");

    private String descricao;

    EnumVoto(String descricao) {
        this.descricao = descricao;
    }

    public String getDescricao() {
        return descricao;
    }

}
