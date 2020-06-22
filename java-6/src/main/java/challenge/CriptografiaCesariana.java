package challenge;

public class CriptografiaCesariana implements Criptografia {

    @Override
    public String criptografar(String texto) {
        if (texto.length() == 0){
            IllegalArgumentException error = new IllegalArgumentException();
            throw error;
        }
        String cifrado = "";
        char criptografia = 3;
        for (char caracter : texto.toCharArray()){
            if (Character.isLetter(caracter)){
                char caracterCifrado = (char) (caracter + criptografia);
                cifrado += (caracterCifrado);
            }else {
                cifrado += caracter;
            }
        }
        return cifrado.toLowerCase();
    }

    @Override
    public String descriptografar(String texto) {
        if (texto.length() == 0){
            IllegalArgumentException error = new IllegalArgumentException();
            throw error;
        }
        String decifrado = "";
        char criptografia = 3;
        for (char caracter : texto.toCharArray()){
            if (Character.isLetter(caracter)){
                char caracterDecifrado = (char) (caracter - criptografia);
                decifrado += (caracterDecifrado);
            }else {
                decifrado += caracter;
            }
        }
        return decifrado.toLowerCase();
    }

}

