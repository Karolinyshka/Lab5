package by.victoria.server;

import by.victoria.common.ConnectionTCP;
import by.victoria.entity.Command;
import by.victoria.entity.Country;
import by.victoria.server.repository.CountryRepository;

import java.net.Socket;
import java.util.List;

public class RequestHandler implements Runnable {
    private final ConnectionTCP connectionTCP;

    public RequestHandler(Socket socket) {
        connectionTCP = new ConnectionTCP(socket);//сокет соединения с клиентом
    }

    @Override
    public void run() {
        CountryRepository countryRepository = new CountryRepository();
        while (true) {
            Command command = (Command) connectionTCP.readObject();
            System.out.println(command);
            switch (command) {
                case CREATE: {
                    Country country = (Country) connectionTCP.readObject();
                    countryRepository.addCountry(country);
                }
                break;
                case READ: {
                    List<Country> countries = countryRepository.getAllCountries();
                    connectionTCP.writeObject(countries);
                }
                break;
                case UPDATE: {
                    Country country = (Country) connectionTCP.readObject();
                    countryRepository.updateCountry(country);
                }
                break;
                case DELETE: {
                    Integer id = (Integer) connectionTCP.readObject();
                    countryRepository.deleteCountryByID(id);
                }
                break;
                case EXIT: {
                    connectionTCP.close();
                    return;
                }
            }
        }
    }
}
