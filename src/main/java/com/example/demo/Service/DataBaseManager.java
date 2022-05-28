package com.example.demo.Service;

import com.example.demo.Entities.Quote;
import com.example.demo.Entities.User;
import com.example.demo.Repos.QuotesRepo;
import com.example.demo.Repos.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Random;

@Service
public class DataBaseManager {

    @Autowired
    private QuotesRepo QRepo;

    @Autowired
    private UserRepo UR;

    private final Random rnd = new Random();

    public Quote getQuote(String type){
        List<Quote> list = QRepo.findByType(type);
        return list.get(rnd.nextInt(list.size()));
    }

    public void addUser(User user){
        UR.save(user);
    }

    public Boolean isUserValid(User user){
        List<User> list = UR.findUser(user.getEmail());
        User validUser = list.get(0);
        return validUser.getPassword().equals(user.getPassword());
    }

    private void addQuote(String type, String text, String author){
        Quote quote = new Quote();
        quote.setType(type);
        quote.setAuthor(author);
        quote.setText(text);
        QRepo.save(quote);
    }

    public void addAll(){
        if(QRepo.findAll().size() == 0){
            String str = "inspirational---Be yourself; everyone else is already taken.---Oscar Wilde&&&\n" +
                    "inspirational---Be the change that you wish to see in the world.---Mahatma Gandhi&&&\n" +
                    "inspirational---q---a&&&\n" +
                    "love---A friend is someone who knows all about you and still loves you.---Elbert Hubbard&&&\n" +
                    "love---We accept the love we think we deserve.---Stephen Chbosky&&&\n" +
                    "love---It is not a lack of love, but a lack of friendship that makes unhappy marriages.---Friedrich Nietzsche&&&\n" +
                    "life---You only live once, but if you do it right, once is enough.---Mae West&&&\n" +
                    "life---In three words I can sum up everything I've learned about life: it goes on.---Robert Frost&&&\n" +
                    "life---It is better to be hated for what you are than to be loved for what you are not.---Andre Gide&&&\n" +
                    "humor---Two things are infinite: the universe and human stupidity; and I'm not sure about the universe.---Albert Einstein&&&\n" +
                    "humor---I love deadlines. I love the whooshing noise they make as they go by.---Douglas Adams&&&\n" +
                    "humor---I find television very educating. Every time somebody turns on the set, I go into the other room and read a book.---Groucho Marx&&&\n" +
                    "wisdom---Whenever you find yourself on the side of the majority, it is time to reform (or pause and reflect).---Mark Twain&&&\n" +
                    "wisdom---It is better to remain silent at the risk of being thought a fool, than to talk and remove all doubt of it.---Maurice Switzer&&&\n" +
                    "wisdom---Knowing yourself is the beginning of all wisdom.---Aristotle&&&\n" +
                    "happiness---For every minute you are angry you lose sixty seconds of happiness.---Ralph Waldo Emerson&&&\n" +
                    "happiness---Folks are usually about as happy as they make their minds up to be.---Abraham Lincoln&&&\n" +
                    "happiness---Happiness in intelligent people is the rarest thing I know.---Ernest Hemingway";
            String[] quotes = str.split("&&&");
            for(String s : quotes){
                String[] ss = s.trim().split("---");
                addQuote(ss[0], ss[1], ss[2]);
            }
        }
    }

    public List<Quote> getTypes(){
        return QRepo.getTypes();
    }
}
