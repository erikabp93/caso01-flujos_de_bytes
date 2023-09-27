package flujosdata;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.io.DataInputStream;
import java.io.DataOutputStream;

public class Persistencia {
	private File archivo;

	public Persistencia() {
		archivo = new File("partidas.dat");
	}

	public void guardar(Partida partida) throws IOException {
		DataOutputStream dos = new DataOutputStream(new FileOutputStream(archivo, true));
		dos.writeInt(partida.getIdJudador());
		dos.writeUTF(partida.getNomJugador());
		dos.writeLong(partida.getPuntos());
		dos.writeDouble(partida.getTiempo());
		dos.close();
	}

	public Partida leer(int idJugador) throws IOException {
		FileInputStream fis = new FileInputStream(archivo);
		DataInputStream dis = new DataInputStream(fis);
		Partida partida = null;
		boolean encontrado = false;
		while (fis.available() > 0 && !encontrado) {
			partida = leerRegistro(dis);
			if (partida.getIdJudador() == idJugador)
				encontrado = true;
		}
		fis.close();
		dis.close();
		return partida;
	}

	// Ejercicio1
	public List<Partida> leerTodos(int idJugador) throws IOException {
		FileInputStream fis = new FileInputStream(archivo);
		DataInputStream dis = new DataInputStream(fis);
		ArrayList<Partida> partidos = new ArrayList<Partida>();
		Partida partida = null;
		boolean encontrado = false;
		while(fis.available() > 0 && !encontrado) {
			partida = leerRegistro(dis);
			if (partida.getIdJudador() == idJugador) {
				partidos.add(partida);
			}
		}
		fis.close();
		dis.close();
		return partidos;
	}

	// Ejercicio2
	public Partida leerMejorPuntuacion() throws IOException {
		FileInputStream fis = new FileInputStream(archivo);
		DataInputStream dis = new DataInputStream(fis);
		Partida partida = null;
		Partida mayorPuntuacion = null;
		ArrayList<Partida> partidos = new ArrayList<Partida>();
		boolean encontrado = false;
		while (fis.available() > 0 && !encontrado) {
			partida = leerRegistro(dis);
			partidos.add(partida);
		}
		mayorPuntuacion = partidos.get(0);
		for(int i = 0; i < partidos.size(); i++) {
			if (partidos.get(i).getPuntos() > mayorPuntuacion.getPuntos()) {
				mayorPuntuacion = partidos.get(i);
			} else {
				if (partidos.get(i).getPuntos() < mayorPuntuacion.getPuntos()) {
					mayorPuntuacion = mayorPuntuacion;
				}
			}
		}
		return mayorPuntuacion;
	}

	// Ejercicio3
	public Partida leerMejorPuntuacion(int idJugador) throws IOException {
		FileInputStream fis = new FileInputStream(archivo);
		DataInputStream dis = new DataInputStream(fis);
		Partida partida = null;
		Partida mayorPuntuacion = null;
		ArrayList<Partida> partidos = new ArrayList<Partida>();
		boolean encontrado = false;
		while (fis.available() > 0 && !encontrado) {
			partida = leerRegistro(dis);
			if (partida.getIdJudador() == idJugador) {
				partidos.add(partida);
			}
		}
		for(int i = 0; i < partidos.size(); i++) {
			mayorPuntuacion = partidos.get(0);

			if (partidos.get(i).getPuntos() > mayorPuntuacion.getPuntos()) {
				mayorPuntuacion = partidos.get(i);
			} else {
				if (partidos.get(i).getPuntos() < mayorPuntuacion.getPuntos()) {
					mayorPuntuacion = mayorPuntuacion;
				}
			}
		}
		return mayorPuntuacion;
	}

	private Partida leerRegistro(DataInputStream dis) throws IOException {
		Partida partida = new Partida();
		partida.setIdJudador(dis.readInt());
		partida.setNomJugador(dis.readUTF());
		partida.setPuntos(dis.readLong());
		partida.setTiempo(dis.readDouble());
		return partida;
	}

}