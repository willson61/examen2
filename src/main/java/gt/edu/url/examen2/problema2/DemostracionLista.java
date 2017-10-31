package gt.edu.url.examen2.problema2;
/**
 * 
 * @author Will
 *
 */
public class DemostracionLista implements DemoList{

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		DemostracionLista obj = new DemostracionLista();
		List<Integer> miLista = new ListaProblema2<>();
		miLista = obj.crearDemoLista();
		String in = miLista.toString();
		System.out.println(in);
	}

	/**
	 * @return lista de integers con los datos requeridos
	 */
	public List<Integer> crearDemoLista() {
		List<Integer> temp = new ListaProblema2<>();
		temp.add(0, 4);
		temp.add(0, 3);
		temp.add(0, 2);
		temp.add(2, 1);
		temp.add(1, 5);
		temp.add(1, 6);
		temp.add(3, 7);
		temp.add(0, 8);
		return temp;
	}

}
