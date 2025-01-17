package vue;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import beans.ElementCommand;
import beans.Paginateur;
import beans.Person;
import beans.Product;
import beans.Salable;
import beans.Service;
import dao.ProductDao;
import dao.SearchDao;
import dao.ServiceDao;
import dao.UsineDao;

@WebServlet("/ServletSearch")
public class ServletSearch extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private SearchDao searchDao;
	private ProductDao productDao;
	private ServiceDao serviceDao;
	public static final String CONF_DAO_FACTORY = "usinedao";

	public void init() throws ServletException {
		this.serviceDao = ((UsineDao) getServletContext().getAttribute(CONF_DAO_FACTORY)).getServiceDao();
		this.productDao = ((UsineDao) getServletContext().getAttribute(CONF_DAO_FACTORY)).getProductDao();
		this.searchDao = ((UsineDao) getServletContext().getAttribute(CONF_DAO_FACTORY)).getSearchDao();

	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		processRequest(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		processRequest(request, response);
	}

	@SuppressWarnings("unchecked")
	private void processRequest(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String action = request.getParameter("action");
		String keyword = request.getParameter("keyword");
		String type = request.getParameter("types");
		HttpSession session = request.getSession(false);
		ArrayList<Product> listProduits = null;
		ArrayList<Service> listServices = null;
		ArrayList<Person> listPersons = null;
		Long nbreProduit = null;
		Long nbreServices = null;
		Long nbrePersons = null;
		if (session != null) {
			Person person = (Person) session.getAttribute("sessionUtilisateur");
			if (action != null) {
				keyword = (String) session.getAttribute("savekeyword");
				Long begins = (long) session.getAttribute("begins");
				Long ends = (long) session.getAttribute("ends");
				if (action.equals("afficherSousVendables")) {
					Long begin = Long.parseLong(request.getParameter("begin"));
					Long end = Long.parseLong(request.getParameter("end"));
					if (type != null) {
						nbreProduit = searchDao.countProductByKeyWord(person.getId(), keyword);
						nbreServices = searchDao.countServiceByKeyWord(person.getId(), keyword);
						listProduits = searchDao.findProductByKeyWord(keyword, person.getId(), end, begin);
						listServices = searchDao.findServiceByKeyWord(keyword, person.getId(), end, begin);
						session.setAttribute("types", type);
						request.setAttribute("searchProductResults", listProduits);
						request.setAttribute("searchServiceResults", listServices);
						request.setAttribute("totalProduct", nbreProduit);
						request.setAttribute("totalService", nbreServices);
						String messageProuct = "";
						String paginationProduct = "";
						if (nbreProduit <= 0 || nbreProduit == null) {
							messageProuct = "Aucun sous-éléments à afficher!!";
						} else {
							messageProuct = "Liste des éléments trouvés";
						}
						paginationProduct = Paginateur.pagine(nbreProduit, listProduits, request, "search");
						System.out.println("Pagination effectuée!");
						request.setAttribute("paginationProduct", paginationProduct);
						System.out.println("Pagination settée!");
						request.setAttribute("totalProduct", nbreProduit);
						request.setAttribute("messageProuct", messageProuct);
						String messageService = "";
						String paginationService = "";
						if (nbreServices <= 0 || nbreServices == null) {
							messageService = "Aucun sous-éléments à afficher!!";
						} else {
							messageService = "Liste des éléments trouvés";
						}
						paginationService = Paginateur.pagine(nbreServices, listServices, request, "search");
						System.out.println("Pagination effectuée!");
						request.setAttribute("paginationService", paginationService);
						System.out.println("Pagination settée!");
						request.setAttribute("totalService", nbreServices);
						request.setAttribute("messageService", messageService);
					} else {
						if (session.getAttribute("types") != null) {
							session.setAttribute("types", null);
						}
						nbrePersons = searchDao.countPersonByKeyWord(person.getId(), keyword);
						nbreProduit = searchDao.countProductByKeyWord(person.getId(), keyword);
						nbreServices = searchDao.countServiceByKeyWord(person.getId(), keyword);
						listProduits = searchDao.findProductByKeyWord(keyword, person.getId(), end, begin);
						listServices = searchDao.findServiceByKeyWord(keyword, person.getId(), end, begin);
						listPersons = searchDao.findPersonByKeyWord(keyword, person.getId(), end, begin);
						request.setAttribute("searchProductResults", listProduits);
						request.setAttribute("searchServiceResults", listServices);
						request.setAttribute("searchPersonResults", listPersons);
						request.setAttribute("totalProduct", nbreProduit);
						request.setAttribute("totalService", nbreServices);
						request.setAttribute("totalPerson", nbrePersons);
						String messageProuct = "";
						String paginationProduct = "";
						if (nbreProduit <= 0 || nbreProduit == null) {
							messageProuct = "Aucun sous-éléments à afficher!!";
						} else {
							messageProuct = "Liste des éléments trouvés";
						}
						paginationProduct = Paginateur.pagine(nbreProduit, listProduits, request, "search");
						System.out.println("Pagination effectuée!");
						request.setAttribute("paginationProduct", paginationProduct);
						System.out.println("Pagination settée!");
						request.setAttribute("totalProduct", nbreProduit);
						request.setAttribute("messageProuct", messageProuct);
						String messageService = "";
						String paginationService = "";
						if (nbreServices <= 0 || nbreServices == null) {
							messageService = "Aucun sous-éléments à afficher!!";
						} else {
							messageService = "Liste des éléments trouvés";
						}
						paginationService = Paginateur.pagine(nbreServices, listServices, request, "search");
						System.out.println("Pagination effectuée!");
						request.setAttribute("paginationService", paginationService);
						System.out.println("Pagination settée!");
						request.setAttribute("totalService", nbreServices);
						request.setAttribute("messageService", messageService);
						String messagePerson = "";
						String paginationPerson = "";
						if (nbrePersons <= 0 || nbrePersons == null) {
							messagePerson = "Aucun sous-éléments à afficher!!";
						} else {
							messagePerson = "Liste des éléments trouvés";
						}
						paginationPerson = Paginateur.pagine(nbrePersons, listPersons, request, "search");
						session.setAttribute("begins", (long) Paginateur.getBegin());
						session.setAttribute("ends", (long) Paginateur.getEnd());
						System.out.println("Pagination effectuée!");
						request.setAttribute("paginationPerson", paginationPerson);
						System.out.println("Pagination settée!");
						request.setAttribute("totalPerson", nbrePersons);
						request.setAttribute("messagePerson", messagePerson);
					}
				}
				if (action.equals("chargerPanier")) {
					ArrayList<ElementCommand> monPanier = null;
					Double totalpanier = 0.0;
					String typea = request.getParameter("typea");
					Long idp = Long.parseLong(request.getParameter("codearticle"));
					Salable salable = null;
					if (session.getAttribute("monPanier") != null) {
						monPanier = ((ArrayList<ElementCommand>) session.getAttribute("monPanier"));
					} else {
						monPanier = new ArrayList<>();
					}
					if (typea.equals("Service")) {
						salable = serviceDao.findServiceById(idp);
					} else {
						salable = productDao.findProductById(idp);
					}
					ElementCommand article = new ElementCommand();
					article.setmProduct(salable);
					monPanier.add(article);
					session.setAttribute("nbrelementspanier", monPanier.size());
					request.setAttribute("articlesPanier", monPanier);
					session.setAttribute("monPanier", monPanier);

					for (int i1 = 0; i1 < monPanier.size(); i1++) {
						totalpanier += monPanier.get(i1).getmProduct().getPrice();
					}
					session.setAttribute("prixtotal", totalpanier);
					if (type != null) {
						nbreProduit = searchDao.countProductByKeyWord(person.getId(), keyword);
						nbreServices = searchDao.countServiceByKeyWord(person.getId(), keyword);
						listProduits = searchDao.findProductByKeyWord(keyword, person.getId(), ends, begins);
						listServices = searchDao.findServiceByKeyWord(keyword, person.getId(), ends, begins);
						session.setAttribute("types", type);
						request.setAttribute("searchProductResults", listProduits);
						request.setAttribute("searchServiceResults", listServices);
						request.setAttribute("totalProduct", nbreProduit);
						request.setAttribute("totalService", nbreServices);
						String messageProuct = "";
						String paginationProduct = "";
						if (nbreProduit <= 0 || nbreProduit == null) {
							messageProuct = "Aucun sous-éléments à afficher!!";
						} else {
							messageProuct = "Liste des éléments trouvés";
						}
						paginationProduct = Paginateur.pagine(nbreProduit, listProduits, request, "search");
						System.out.println("Pagination effectuée!");
						request.setAttribute("paginationProduct", paginationProduct);
						System.out.println("Pagination settée!");
						request.setAttribute("totalProduct", nbreProduit);
						request.setAttribute("messageProuct", messageProuct);
						String messageService = "";
						String paginationService = "";
						if (nbreServices <= 0 || nbreServices == null) {
							messageService = "Aucun sous-éléments à afficher!!";
						} else {
							messageService = "Liste des éléments trouvés";
						}
						paginationService = Paginateur.pagine(nbreServices, listServices, request, "search");
						System.out.println("Pagination effectuée!");
						request.setAttribute("paginationService", paginationService);
						System.out.println("Pagination settée!");
						request.setAttribute("totalService", nbreServices);
						request.setAttribute("messageService", messageService);
					} else {
						if (session.getAttribute("types") != null) {
							session.setAttribute("types", null);
						}
						nbrePersons = searchDao.countPersonByKeyWord(person.getId(), keyword);
						nbreProduit = searchDao.countProductByKeyWord(person.getId(), keyword);
						nbreServices = searchDao.countServiceByKeyWord(person.getId(), keyword);
						listProduits = searchDao.findProductByKeyWord(keyword, person.getId(), ends, begins);
						listServices = searchDao.findServiceByKeyWord(keyword, person.getId(), ends, begins);
						listPersons = searchDao.findPersonByKeyWord(keyword, person.getId(), ends, begins);
						request.setAttribute("searchProductResults", listProduits);
						request.setAttribute("searchServiceResults", listServices);
						request.setAttribute("searchPersonResults", listPersons);
						request.setAttribute("totalProduct", nbreProduit);
						request.setAttribute("totalService", nbreServices);
						request.setAttribute("totalPerson", nbrePersons);
						String messageProuct = "";
						String paginationProduct = "";
						if (nbreProduit <= 0 || nbreProduit == null) {
							messageProuct = "Aucun sous-éléments à afficher!!";
						} else {
							messageProuct = "Liste des éléments trouvés";
						}
						paginationProduct = Paginateur.pagine(nbreProduit, listProduits, request, "search");
						System.out.println("Pagination effectuée!");
						request.setAttribute("paginationProduct", paginationProduct);
						System.out.println("Pagination settée!");
						request.setAttribute("totalProduct", nbreProduit);
						request.setAttribute("messageProuct", messageProuct);
						String messageService = "";
						String paginationService = "";
						if (nbreServices <= 0 || nbreServices == null) {
							messageService = "Aucun sous-éléments à afficher!!";
						} else {
							messageService = "Liste des éléments trouvés";
						}
						paginationService = Paginateur.pagine(nbreServices, listServices, request, "search");
						System.out.println("Pagination effectuée!");
						request.setAttribute("paginationService", paginationService);
						System.out.println("Pagination settée!");
						request.setAttribute("totalService", nbreServices);
						request.setAttribute("messageService", messageService);
						String messagePerson = "";
						String paginationPerson = "";
						if (nbrePersons <= 0 || nbrePersons == null) {
							messagePerson = "Aucun sous-éléments à afficher!!";
						} else {
							messagePerson = "Liste des éléments trouvés";
						}
						paginationPerson = Paginateur.pagine(nbrePersons, listPersons, request, "search");
						session.setAttribute("begins", (long) Paginateur.getBegin());
						session.setAttribute("ends", (long) Paginateur.getEnd());
						System.out.println("Pagination effectuée!");
						request.setAttribute("paginationPerson", paginationPerson);
						System.out.println("Pagination settée!");
						request.setAttribute("totalPerson", nbrePersons);
						request.setAttribute("messagePerson", messagePerson);
					}
				}

			} else {
				session.setAttribute("begins", (long) 0);
				session.setAttribute("ends", (long) 5);
				session.setAttribute("savekeyword", keyword);
				if (type != null) {
					nbreProduit = searchDao.countProductByKeyWord(person.getId(), keyword);
					nbreServices = searchDao.countServiceByKeyWord(person.getId(), keyword);
					listProduits = searchDao.findProductByKeyWord(keyword, person.getId(), (long) 5, (long) 0);
					listServices = searchDao.findServiceByKeyWord(keyword, person.getId(), (long) 5, (long) 0);
					session.setAttribute("types", type);
					request.setAttribute("searchProductResults", listProduits);
					request.setAttribute("searchServiceResults", listServices);
					request.setAttribute("totalProduct", nbreProduit);
					request.setAttribute("totalService", nbreServices);
					String messageProuct = "";
					String paginationProduct = "";
					if (nbreProduit <= 0 || nbreProduit == null) {
						messageProuct = "Aucun sous-éléments à afficher!!";
					} else {
						messageProuct = "Liste des éléments trouvés";
					}
					paginationProduct = Paginateur.pagine(nbreProduit, listProduits, request, "search");
					System.out.println("Pagination effectuée!");
					request.setAttribute("paginationProduct", paginationProduct);
					System.out.println("Pagination settée!");
					request.setAttribute("totalProduct", nbreProduit);
					request.setAttribute("messageProuct", messageProuct);
					String messageService = "";
					String paginationService = "";
					if (nbreServices <= 0 || nbreServices == null) {
						messageService = "Aucun sous-éléments à afficher!!";
					} else {
						messageService = "Liste des éléments trouvés";
					}
					paginationService = Paginateur.pagine(nbreServices, listServices, request, "search");
					System.out.println("Pagination effectuée!");
					request.setAttribute("paginationService", paginationService);
					System.out.println("Pagination settée!");
					request.setAttribute("totalService", nbreServices);
					request.setAttribute("messageService", messageService);
				} else {
					if (session.getAttribute("types") != null) {
						session.setAttribute("types", null);
					}
					nbrePersons = searchDao.countPersonByKeyWord(person.getId(), keyword);
					nbreProduit = searchDao.countProductByKeyWord(person.getId(), keyword);
					nbreServices = searchDao.countServiceByKeyWord(person.getId(), keyword);
					listProduits = searchDao.findProductByKeyWord(keyword, person.getId(), (long) 5, (long) 0);
					listServices = searchDao.findServiceByKeyWord(keyword, person.getId(), (long) 5, (long) 0);
					listPersons = searchDao.findPersonByKeyWord(keyword, person.getId(), (long) 5, (long) 0);
					request.setAttribute("searchProductResults", listProduits);
					request.setAttribute("searchServiceResults", listServices);
					request.setAttribute("searchPersonResults", listPersons);
					request.setAttribute("totalProduct", nbreProduit);
					request.setAttribute("totalService", nbreServices);
					request.setAttribute("totalPerson", nbrePersons);
					String messageProuct = "";
					String paginationProduct = "";
					if (nbreProduit <= 0 || nbreProduit == null) {
						messageProuct = "Aucun sous-éléments à afficher!!";
					} else {
						messageProuct = "Liste des éléments trouvés";
					}
					paginationProduct = Paginateur.pagine(nbreProduit, listProduits, request, "search");
					System.out.println("Pagination effectuée!");
					request.setAttribute("paginationProduct", paginationProduct);
					System.out.println("Pagination settée!");
					request.setAttribute("totalProduct", nbreProduit);
					request.setAttribute("messageProuct", messageProuct);
					String messageService = "";
					String paginationService = "";
					if (nbreServices <= 0 || nbreServices == null) {
						messageService = "Aucun sous-éléments à afficher!!";
					} else {
						messageService = "Liste des éléments trouvés";
					}
					paginationService = Paginateur.pagine(nbreServices, listServices, request, "search");
					System.out.println("Pagination effectuée!");
					request.setAttribute("paginationService", paginationService);
					System.out.println("Pagination settée!");
					request.setAttribute("totalService", nbreServices);
					request.setAttribute("messageService", messageService);
					String messagePerson = "";
					String paginationPerson = "";
					if (nbrePersons <= 0 || nbrePersons == null) {
						messagePerson = "Aucun sous-éléments à afficher!!";
					} else {
						messagePerson = "Liste des éléments trouvés";
					}
					paginationPerson = Paginateur.pagine(nbrePersons, listPersons, request, "search");
					System.out.println("Pagination effectuée!");
					request.setAttribute("paginationPerson", paginationPerson);
					System.out.println("Pagination settée!");
					request.setAttribute("totalPerson", nbrePersons);
					request.setAttribute("messagePerson", messagePerson);
				}
			}
			this.getServletContext().getRequestDispatcher("/WEB-INF/search.jsp").forward(request, response);

		} else {
			response.sendRedirect(request.getContextPath() + "/connexion");

		}

	}
}
