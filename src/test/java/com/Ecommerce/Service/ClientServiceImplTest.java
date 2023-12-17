/*package com.Ecommerce.Service;
import com.Ecommerce.Entity.DetailFacture;
import com.Ecommerce.Entity.Facture;
import com.Ecommerce.Entity.Produit;
import com.Ecommerce.Entity.client;
import com.Ecommerce.Repositry.ClientRepositry;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import java.util.*;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@SpringBootTest
@ExtendWith(MockitoExtension.class)
@RunWith(MockitoJUnitRunner.class)
class ClientServiceImplTest {

    @Mock
    private ClientRepositry clientRepositoryMok;

    @InjectMocks
    private ClientServiceImpl clientService;

    @Test
    public void testAddClient() {
        client client = new client(Math.toIntExact(2L),"cL2","salh","ben mabrouk","benzart",true,234568779,"salh@gmail.com");
        lenient().when(clientRepositoryMok.save(client)).thenReturn(client);
        client savedClient = clientService.addclient(client);
        assertNotNull(savedClient);
        // erreur save du client
        lenient().when(clientRepositoryMok.save(client)).thenThrow(new RuntimeException("Erreur lors de l'enregistrement du client"));
        assertThrows(RuntimeException.class, () -> {
            clientService.addclient(client);
        });
        assertEquals(client, savedClient);
       // assertEquals("ali", savedClient.getNom());
        assertEquals("ben mabrouk", savedClient.getPrenom());
    }

    @Test
    public void testUpdateClient() {
        client client = new client(Math.toIntExact(2L),"cL2","salh","ben mabrouk","benzart",true,234568779,"salh@gmail.com");
        lenient().when(clientRepositoryMok.findById(2L)).thenReturn(Optional.of(client));
        lenient().when(clientRepositoryMok.save(client)).thenReturn(client);
        client updatedClient = clientService.Updateclient(client, 2L);
        assertNotNull(updatedClient);
        // Cas le client non trouvé
        Long nonExistingClientId = 5L;
        lenient().when(clientRepositoryMok.findById(nonExistingClientId)).thenReturn(Optional.empty());
        lenient().when(clientRepositoryMok.findById(nonExistingClientId)).thenThrow(new RuntimeException("Erreur lors de l'update du client"));
        assertThrows(RuntimeException.class, () -> {
            clientService.Updateclient(client, nonExistingClientId);
        });
        // assertEquals(client, clientService.Updateclient(client, nonExistingClientId));

        Mockito.verify(clientRepositoryMok, Mockito.times(1)).findById(5L);
        Mockito.verify(clientRepositoryMok, Mockito.times(1)).findById(2L);

        Mockito.verify(clientRepositoryMok, Mockito.times(1)).save(client);
    }

    @Test
    public void testAddListClient() {
        client client1 = new client(Math.toIntExact(3L),"cl3","jalel","jlasi", "tunise", true, 123456789, "jlasi@gmail.com");
        client client2 = new client(Math.toIntExact(4L),"cl4","manar","ben ali", "sousa", true, 987654321, "manar@gmail.com");
        List<client> clientList = Arrays.asList(client1, client2);
        lenient().when(clientRepositoryMok.saveAll(clientList)).thenReturn(clientList);
        List<client> savedClients = clientService.addListclient(clientList);
        assertNotNull(savedClients);
        assertEquals(2, savedClients.size());// Vérifier le save
        assertEquals(clientList.get(0), savedClients.get(0));
        assertEquals(clientList.get(1), savedClients.get(1));//  d'erreur save d'un client
        when(clientRepositoryMok.saveAll(clientList)).thenThrow(new RuntimeException("Erreur lors de l'enregistrement de liste des clients"));
        assertThrows(RuntimeException.class, () -> {
            clientService.addListclient(clientList);});//  liste de clients est vide
        List<client> emptyClientList = Collections.emptyList();
        when(clientRepositoryMok.saveAll(emptyClientList)).thenReturn(Collections.emptyList());
        List<client> savedEmptyClients = clientService.addListclient(emptyClientList);//cas d'erreur
   // assertEquals(clientList, savedEmptyClients);
        assertNotNull(savedEmptyClients);
        assertTrue(savedEmptyClients.isEmpty());

    }
    @Test
    public void testGetAllClients() {
        client client1 = new client(Math.toIntExact(1L), "nom1", "prenom1", "adresse1", "ville1", true, 123456789, "email1@gmail.com");
        client client2 = new client(Math.toIntExact(2L), "nom2", "prenom2", "adresse2", "ville2", true, 987654321, "email2@gmail.com");
        List<client> clientList = Arrays.asList(client1, client2);
        when(clientRepositoryMok.findAll()).thenReturn(clientList);
        List<client> result = clientService.getallclients();
        assertNotNull(result);
        assertEquals(clientList.size(), result.size());
        assertEquals(clientList, result);// erreur  tous les clients
        lenient().when(clientRepositoryMok.findAll()).thenThrow(new RuntimeException("Erreur lors de la récupération des clients"));
        assertThrows(RuntimeException.class, () -> {
            clientService.getallclients();
        });
       // assertEquals(result, clientService.getallclients());
    }

    @Test
    public void testFindByNom() {
        String nomToFind = "sami";
        client clientToFind = new client(Math.toIntExact(6L), nomToFind, "sami", "salhi", "sousa", true, 123456789, "sami@gmail.com");
        lenient().when(clientRepositoryMok.findByNom(nomToFind)).thenReturn(clientToFind);
        client result = clientService.findByNom(nomToFind);
        assertNotNull(result);
        assertEquals(clientToFind, result);
        String nomNonExistant = "nouha";
       when(clientRepositoryMok.findByNom(nomNonExistant)).thenThrow(new RuntimeException("Erreur lors de la recherche par nom"));
        assertThrows(RuntimeException.class, () -> {
           clientService.findByNom(nomNonExistant);
        });
       // assertEquals(result, nomNonExistant);
    }
    @Test
    public void testGetClientById() {
        Long clientId = 1L;
        client client = new client();
        when(clientRepositoryMok.getclientById(clientId)).thenReturn(client);
        client result = clientService.getclientById(clientId);// Vérifier le résultat
        assertEquals(client, result);//  le client n'est pas trouvé
        when(clientRepositoryMok.getclientById(8L)).thenReturn(null);
        when(clientRepositoryMok.getclientById(8L)).thenThrow(new RuntimeException("Erreur lors de la recherche par id"));
        assertThrows(RuntimeException.class, () -> {
            clientService.getclientById(8L);
        });
      //assertEquals(result, 8L);
       // assertNull(clientService.getclientById(8L));
    }

    @Test
    public void testGetMostPurchasedProducts() {
        client client = new client();
        Facture facture = new Facture();
        DetailFacture detailFacture = new DetailFacture();
        Produit produit = new Produit();
        produit.setCodep("code123");
        detailFacture.setProduit(produit);
        facture.setDetailFactures(Set.of(detailFacture));
        client.setFactures(Set.of(facture));
        Map<String, Long> result = clientService.getMostPurchasedProducts(client);
     //   assertEquals(2L, result.size());
        assertTrue(result.containsKey("code123"));
        assertEquals(1L, result.get("code123"));
        client.setFactures(Collections.emptySet());
        Map<String, Long> emptyResult = clientService.getMostPurchasedProducts(client);
        assertTrue(emptyResult.isEmpty());
    }

    @Test
    public void testGetFacturesRegleesPourClient() {
        Long clientId = 1L;
        Facture facture1 = new Facture();
        Facture facture2 = new Facture();
        when(clientRepositoryMok.findFacturesRegleesPourClient(clientId)).thenReturn(Arrays.asList(facture1, facture2));
        List<Facture> result = clientService.getFacturesRegleesPourClient(clientId);
        // Vérifier le résultat
//        assertEquals(3, result.size());
        assertTrue(result.contains(facture1));
     //    assertEquals(false, result.contains(facture1));
        assertTrue(result.contains(facture2));
    }

    @Test
    public void testGetFacturesNonRegleesPourClient() {
        Long clientId = 1L;
        Facture facture1 = new Facture();
        Facture facture2 = new Facture();
        when(clientRepositoryMok.findFacturesNonRegleesPourClient(clientId)).thenReturn(Arrays.asList(facture1, facture2));
        List<Facture> result = clientService.getFacturesNonRegleesPourClient(clientId);
        assertEquals(2, result.size());
      //  assertEquals(3, result.size());
        assertTrue(result.contains(facture1));
      //  assertEquals(false, result.contains(facture1));
        assertTrue(result.contains(facture2));
    }

    @Test
    public void testCalculerChiffreAffairesGlobal() {
        Long clientId = 1L;
        client client = new client();
        Facture facture1 = new Facture();
        Facture facture2 = new Facture();
        DetailFacture detailFacture1 = new DetailFacture();
        DetailFacture detailFacture2 = new DetailFacture();
        detailFacture1.setQte(2L);
        detailFacture1.setPrixunitaire(25.0);
        detailFacture2.setQte(3L);
        detailFacture2.setPrixunitaire(30.0);
        facture1.setDetailFactures(Set.of(detailFacture1));
        facture2.setDetailFactures(Set.of(detailFacture2));
        client.setFactures(Set.of(facture1, facture2));
        when(clientRepositoryMok.findById(clientId)).thenReturn(Optional.of(client));
        double result = clientService.calculerChiffreAffairesGlobal(clientId);
        assertEquals(140,result);
        // Cas où le client n'est pas trouvé
        when(clientRepositoryMok.findById(2L)).thenReturn(Optional.empty());
        assertEquals(0.0, clientService.calculerChiffreAffairesGlobal(2L));
    }
    @Test
    public void testCalculerChiffreAffairesParAn() {
        Long clientId = 1L;
        int annee = 2022;
        client client = new client();
        Facture facture1 = new Facture();
        Facture facture2 = new Facture();
        facture1.setDateFact("2022-01-15");
        facture2.setDateFact("2022-03-20");
        client.setFactures(Set.of(facture1, facture2));
        when(clientRepositoryMok.findById(clientId)).thenReturn(Optional.of(client));
        double result = clientService.calculerChiffreAffairesParAn(clientId, annee);
        // Vérifier le résultat
        assertEquals(0.0, result);
    }



    @Test
    public void testCalculerMontantTotal() {
        client client = new client();
        Facture facture = new Facture();
        DetailFacture detailFacture1 = new DetailFacture();
        DetailFacture detailFacture2 = new DetailFacture();
        detailFacture1.setQte(2L);
        detailFacture1.setPrixunitaire(25.0);
        detailFacture2.setQte(3L);
        detailFacture2.setPrixunitaire(30.0);
        facture.setDetailFactures(Set.of(detailFacture1, detailFacture2));// Cas nominal
        double result = clientService.calculerMontantTotal(facture);// Vérifier le résultat attendu
       // assertEquals(2 * 25.0 + 3 * 30.0, result);// une exception  l'enregistrement des clients
        when(clientRepositoryMok.save(client)).thenThrow(new RuntimeException("Erreur simulée lors de la sauvegarde du client"));// Vérifier que l'exception est bien levée
        assertThrows(RuntimeException.class, () -> clientService.addclient(client));
        //assertEquals(14, result);
    }


    @Test
    public void testIsFacturePourAnnee() {
        Facture facture = new Facture();
        facture.setDateFact("2022-05-10");
        //boolean result = clientService.isFacturePourAnnee(facture, 2022);
        // Vérifier le résultat
       // assertTrue(result, "La facture devrait être considérée comme étant de l'année 2022.");
        // Vérifier la date de la facture après l'appel de la méthode
       assertEquals("2022-05-10", facture.getDateFact(), "La date de la facture ne devrait pas être modifiée.");

    }


    @Test
    public void testDeleteClient() {
        Long clientId = 1L;
        // le client existe
        client existingClient = new client(Math.toIntExact(clientId), "cL2", "salh", "ben mabrouk", "benzart", true, 234568779, "salh@gmail.com");
        lenient().when(clientRepositoryMok.findById(clientId)).thenReturn(Optional.of(existingClient));
        // le client n'existe pas
        lenient().when(clientRepositoryMok.findById(clientId)).thenReturn(Optional.empty());
        doThrow(new RuntimeException("Erreur lors de la suppression du client inexistant")).when(clientRepositoryMok).deleteById(clientId);
        assertThrows(RuntimeException.class, () -> clientService.deleteclient(clientId));
      // assertEquals(existingClient, clientService.deleteclient(clientId));
        verify(clientRepositoryMok, times(1)).deleteById(clientId);

    }


}*/
