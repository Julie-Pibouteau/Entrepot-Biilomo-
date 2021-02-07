package personne;

/**
 * On va utiliser Tuple soit pour représenter des coordonnées Tuple<Integer abs, Integer ord>,
 * soit pour representer Tuple<String nomLotDePiece, Integer quantite> quand il est utilisé lors de la commande
 * 
 * @author Lauréline
 *
 */

public class Tuple<X, Y> {
	private X x;
	private Y y;

	/**
	 * constructeur de Tuple
	 * 
	 * @param x
	 * @param y
	 */
	public Tuple(X x, Y y) {
		this.x = x;
		this.y = y;
	}

	public X getX() throws NullPointerException {
		return x;
	}

	public Y getY() throws NullPointerException {
		return y;
	}

	public void afficherTuple() {
		System.out.println("coord : (" + x + "," + y + ")");
	}

	@Override
	public boolean equals(Object o) {
		if (o instanceof Tuple) {
			Tuple<X, Y> t = (Tuple<X, Y>) o;
			if ((this.x.equals(t.getX())) && (this.y.equals(t.getY()))) {
				return true;
			}
		}
		return false;
	}

}