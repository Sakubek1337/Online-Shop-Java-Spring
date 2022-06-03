package com.example.demo.Service;

import com.example.demo.Models.Entities.Quote;
import com.example.demo.Models.Entities.TypeInfo;
import com.example.demo.Models.Entities.User;
import com.example.demo.Repos.QuotesRepo;

import com.example.demo.Repos.TypeRepo;
import com.example.demo.Repos.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.Random;

@Service
public class DataBaseManager {

    @Autowired
    private QuotesRepo QRepo;

    @Autowired
    private UserRepo UR;

    @Autowired
    private TypeRepo TR;

    private final Random rnd = new Random();

    public Quote getQuote(String type){
        List<Quote> list = QRepo.findByType(type);
        return list.get(rnd.nextInt(list.size()));
    }

    public boolean checkUser(String username){
        Optional<User> user = UR.findByUsername(username);
        return user.isPresent();
    }

    public void addUser(User user){
        UR.save(user);
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
                    "inspirational---Imperfection is beauty, madness is genius and it's better to be absolutely ridiculous than absolutely boring.---Marilyn Monroe&&&\n" +
                    "inspirational---Do one thing every day that scares you.---Eleanor Roosevelt&&&\n" +
                    "love---A friend is someone who knows all about you and still loves you.---Elbert Hubbard&&&\n" +
                    "love---We accept the love we think we deserve.---Stephen Chbosky&&&\n" +
                    "love---It is not a lack of love, but a lack of friendship that makes unhappy marriages.---Friedrich Nietzsche&&&\n" +
                    "love---Love all, trust a few, do wrong to none.---William Shakespeare&&&\n" +
                    "life---You only live once, but if you do it right, once is enough.---Mae West&&&\n" +
                    "life---In three words I can sum up everything I've learned about life: it goes on.---Robert Frost&&&\n" +
                    "life---It is better to be hated for what you are than to be loved for what you are not.---Andre Gide&&&\n" +
                    "life---Insanity is doing the same thing, over and over again, but expecting different results.---Anonymous&&&\n" +
                    "humor---Two things are infinite: the universe and human stupidity; and I'm not sure about the universe.---Albert Einstein&&&\n" +
                    "humor---I love deadlines. I love the whooshing noise they make as they go by.---Douglas Adams&&&\n" +
                    "humor---Never put off till tomorrow what may be done day after tomorrow just as well.---Mark Twain&&&\n" +
                    "humor---I find television very educating. Every time somebody turns on the set, I go into the other room and read a book.---Groucho Marx&&&\n" +
                    "wisdom---Whenever you find yourself on the side of the majority, it is time to reform (or pause and reflect).---Mark Twain&&&\n" +
                    "wisdom---It is better to remain silent at the risk of being thought a fool, than to talk and remove all doubt of it.---Maurice Switzer&&&\n" +
                    "wisdom---Knowing yourself is the beginning of all wisdom.---Aristotle&&&\n" +
                    "wisdom---The fool doth think he is wise, but the wise man knows himself to be a fool.---William Shakespeare&&&\n" +
                    "happiness---For every minute you are angry you lose sixty seconds of happiness.---Ralph Waldo Emerson&&&\n" +
                    "happiness---Folks are usually about as happy as they make their minds up to be.---Abraham Lincoln&&&\n" +
                    "happiness---Happiness is not something ready made. It comes from your own actions.---Dalai Lama&&&\n" +
                    "happiness---Happiness in intelligent people is the rarest thing I know.---Ernest Hemingway";

            String[] quotes = str.split("&&&");
            for(String s : quotes){
                String[] ss = s.trim().split("---");
                addQuote(ss[0], ss[1], ss[2]);
            }
        }
    }

    public void addTypeInfo(){
        if(TR.findAll().size() == 0){
            TypeInfo ti = new TypeInfo();
            ti.setId(1);
            ti.setType_label("LIFE QUOTES");
            ti.setImage_url("https://images.wallpaperscraft.ru/image/single/zakat_derevo_ozero_130063_1920x1080.jpg");
            TR.save(ti);
            ti = new TypeInfo();
            ti.setId(2);
            ti.setType_label("LOVE QUOTES");
            ti.setImage_url("https://images.wallpaperscraft.ru/image/single/para_ruki_nezhnost_118110_1920x1080.jpg");
            TR.save(ti);
            ti = new TypeInfo();
            ti.setId(3);
            ti.setType_label("INSPIRATIONAL QUOTES");
            ti.setImage_url("https://images.wallpaperscraft.ru/image/single/zima_sneg_siluet_131193_1920x1080.jpg");
            TR.save(ti);
            ti = new TypeInfo();
            ti.setId(4);
            ti.setType_label("HUMOR QUOTES");
            ti.setImage_url("https://images.wallpaperscraft.ru/image/single/miach_smajl_smajlik_147563_1920x1080.jpg");
            TR.save(ti);
            ti = new TypeInfo();
            ti.setId(5);
            ti.setType_label("WISDOM QUOTES");
            ti.setImage_url("https://images.wallpaperscraft.ru/image/single/kniga_tyulpany_kofe_117577_1920x1080.jpg");
            TR.save(ti);
            ti = new TypeInfo();
            ti.setId(6);
            ti.setType_label("HAPPINESS QUOTES");
            ti.setImage_url("https://images.wallpaperscraft.ru/image/single/schenok_kotenok_trava_cvety_para_druzhba_29330_1920x1080.jpg");
            TR.save(ti);
        }
    }

    public List<TypeInfo> getTypes(){
        return TR.findAll();
    }
}
