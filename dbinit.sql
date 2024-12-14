
CREATE TABLE public.actors (
                               id integer NOT NULL,
                               name character varying(255) NOT NULL,
                               bio character varying(1500),
                               year_of_birth bigint,
                               photo_path character varying(255),
                               height bigint
);


ALTER TABLE public.actors OWNER TO postgres;

--
-- TOC entry 217 (class 1259 OID 18981)
-- Name: actors_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.actors_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER SEQUENCE public.actors_id_seq OWNER TO postgres;



ALTER SEQUENCE public.actors_id_seq OWNED BY public.actors.id;


--


CREATE TABLE public.directors (
                                  id integer NOT NULL,
                                  name character varying(255) NOT NULL,
                                  bio character varying(1500),
                                  date_of_birth date,
                                  photo_path character varying(255)
);


ALTER TABLE public.directors OWNER TO postgres;

--
-- TOC entry 219 (class 1259 OID 18992)
-- Name: directors_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.directors_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER SEQUENCE public.directors_id_seq OWNER TO postgres;



ALTER SEQUENCE public.directors_id_seq OWNED BY public.directors.id;




CREATE TABLE public.flyway_schema_history (
                                              installed_rank integer NOT NULL,
                                              version character varying(50),
                                              description character varying(200) NOT NULL,
                                              type character varying(20) NOT NULL,
                                              script character varying(1000) NOT NULL,
                                              checksum integer,
                                              installed_by character varying(100) NOT NULL,
                                              installed_on timestamp without time zone DEFAULT now() NOT NULL,
                                              execution_time integer NOT NULL,
                                              success boolean NOT NULL
);


ALTER TABLE public.flyway_schema_history OWNER TO postgres;



CREATE TABLE public.genres (
                               id integer NOT NULL,
                               name character varying(100) NOT NULL,
                               image_path character varying(255),
                               description character varying(500)
);


ALTER TABLE public.genres OWNER TO postgres;



CREATE SEQUENCE public.genres_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER SEQUENCE public.genres_id_seq OWNER TO postgres;



ALTER SEQUENCE public.genres_id_seq OWNED BY public.genres.id;




CREATE TABLE public.movie_actor (
                                    movie_id bigint NOT NULL,
                                    actor_id bigint NOT NULL
);


ALTER TABLE public.movie_actor OWNER TO postgres;



CREATE TABLE public.movie_genre (
                                    movie_id bigint NOT NULL,
                                    genre_id bigint NOT NULL
);


ALTER TABLE public.movie_genre OWNER TO postgres;



CREATE TABLE public.movies (
                               id integer NOT NULL,
                               title character varying(255) NOT NULL,
                               description character varying(500),
                               country character varying(255),
                               director_id bigint NOT NULL,
                               release_date date,
                               poster_path character varying(255) NOT NULL,
                               length bigint,
                               budget bigint,
                               box_office bigint
);


ALTER TABLE public.movies OWNER TO postgres;


CREATE SEQUENCE public.movies_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER SEQUENCE public.movies_id_seq OWNER TO postgres;


ALTER SEQUENCE public.movies_id_seq OWNED BY public.movies.id;




CREATE TABLE public.ratings (
                                id integer NOT NULL,
                                user_id bigint NOT NULL,
                                movie_id bigint NOT NULL,
                                rating integer NOT NULL,
                                CONSTRAINT ratings_rating_check CHECK (((rating >= 1) AND (rating <= 100)))
);


ALTER TABLE public.ratings OWNER TO postgres;



CREATE SEQUENCE public.ratings_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER SEQUENCE public.ratings_id_seq OWNER TO postgres;



ALTER SEQUENCE public.ratings_id_seq OWNED BY public.ratings.id;



CREATE TABLE public.reviews (
                                id integer NOT NULL,
                                content text NOT NULL,
                                created_at timestamp without time zone DEFAULT CURRENT_TIMESTAMP,
                                user_id integer NOT NULL,
                                movie_id integer NOT NULL
);


ALTER TABLE public.reviews OWNER TO postgres;


CREATE SEQUENCE public.reviews_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER SEQUENCE public.reviews_id_seq OWNER TO postgres;



ALTER SEQUENCE public.reviews_id_seq OWNED BY public.reviews.id;



CREATE TABLE public.users (
                              id integer NOT NULL,
                              username character varying(255) NOT NULL,
                              password character varying(255) NOT NULL,
                              avatar_path character varying(255),
                              role character varying(20) DEFAULT 'CUSTOMER'::character varying NOT NULL
);


ALTER TABLE public.users OWNER TO postgres;



CREATE SEQUENCE public.users_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER SEQUENCE public.users_id_seq OWNER TO postgres;



ALTER SEQUENCE public.users_id_seq OWNED BY public.users.id;




ALTER TABLE ONLY public.actors ALTER COLUMN id SET DEFAULT nextval('public.actors_id_seq'::regclass);



ALTER TABLE ONLY public.directors ALTER COLUMN id SET DEFAULT nextval('public.directors_id_seq'::regclass);


ALTER TABLE ONLY public.genres ALTER COLUMN id SET DEFAULT nextval('public.genres_id_seq'::regclass);




ALTER TABLE ONLY public.movies ALTER COLUMN id SET DEFAULT nextval('public.movies_id_seq'::regclass);




ALTER TABLE ONLY public.ratings ALTER COLUMN id SET DEFAULT nextval('public.ratings_id_seq'::regclass);




ALTER TABLE ONLY public.reviews ALTER COLUMN id SET DEFAULT nextval('public.reviews_id_seq'::regclass);


ALTER TABLE ONLY public.users ALTER COLUMN id SET DEFAULT nextval('public.users_id_seq'::regclass);




INSERT INTO public.actors (id, name, bio, year_of_birth, photo_path, height) VALUES (5, 'Tim Robbins', 'Timothy Francis Robbins (born October 16, 1958)[2] is an American actor. He is best known for portraying Andy Dufresne in the film The Shawshank Redemption (1994), and Jacob Singer in Jacob`s Ladder (1990), as well as winning an Academy Award and Golden Globe award for his role in Mystic River (2003) and another Golden Globe for The Player (1992). Robbins`s other roles include starring as Lt. Samuel "Merlin" Wells in Top Gun (1986), Nuke LaLoosh in Bull Durham (1988), Erik in Erik the Viking (1989), Ed Walters in I.Q. (1994), Nick Beam in Nothing to Lose (1997) and Senator Robert Hammond in Green Lantern (2011). He also directed the films Bob Roberts (1992) and Dead Man Walking (1995), both of which were well received. He received an Academy Award nomination for Best Director for Dead Man Walking. On television, Robbins played Secretary of State Walter Larson in the HBO comedy The Brink (2015), and in Here and Now (2018) portrayed Greg Boatwright. In 2023, he starred as Bernard Holland in the Apple TV+ series Silo.', 1958, 'timrobbins.jpg', 196);
INSERT INTO public.actors (id, name, bio, year_of_birth, photo_path, height) VALUES (6, 'Morgan Freeman', 'Morgan Freeman (born June 1, 1937) is an American actor, producer, and narrator. In a career spanning five decades, he has received numerous accolades, including an Academy Award and a Golden Globe Award, as well as a nomination for a Tony Award. He was honored with the Kennedy Center Honor in 2008, an AFI Life Achievement Award in 2011, the Cecil B. DeMille Award in 2012, and Screen Actors Guild Life Achievement Award in 2018. He is widely regarded as one of the greatest actors of all time. Born in Memphis, Tennessee, Freeman was raised in Mississippi, where he began acting in school plays. He studied theater arts in Los Angeles and appeared in stage productions in his early career. He rose to fame in the 1970s for his role in the children`s television series The Electric Company. Freeman received the Academy Award for Best Supporting Actor for his role as a former boxer in Clint Eastwood`s sports drama Million Dollar Baby (2004). He was Oscar-nominated for Street Smart (1987), Driving Miss Daisy (1989), The Shawshank Redemption (1994), and Invictus (2009). Other notable roles include in Glory (1989), Robin Hood: Prince of Thieves (1991), Se7en (1995), The Bucket List (2007). He also portrayed Lucius Fox in Christopher Nolan`s The Dark Knight Trilogy (2005–2012) and starred in the action films Wanted (2008), Now You See Me (2013), and Lucy (2014).', 1937, 'morganfreeman.jpg', 188);
INSERT INTO public.actors (id, name, bio, year_of_birth, photo_path, height) VALUES (7, 'Tom Hanks', 'Thomas Jeffrey Hanks (born July 9, 1956) is an American actor and filmmaker. Known for both his comedic and dramatic roles, he is one of the most popular and recognizable film stars worldwide, and is regarded as an American cultural icon.[2] Hanks is ranked as the fifth-highest-grossing American film actor.[3][4] Over his career he has received numerous awards including two Academy Awards, seven Emmy Awards and four Golden Globe Awards as well as nominations for five BAFTA Awards and a Tony Award. He has been honored with the AFI Life Achievement Award in 2002, the Kennedy Center Honor in 2014, the Presidential Medal of Freedom in 2016 and the Golden Globe Cecil B. DeMille Award in 2020. Hanks has starred in numerous films cementing his film stardom acting in the romantic comedies Sleepless in Seattle (1993) and You`ve Got Mail (1998), the dramas Apollo 13 (1995), The Green Mile (1999), Road to Perdition (2002) and Cloud Atlas (2012), and the biographical dramas Charlie Wilson`s War (2007), Captain Phillips (2013), A Beautiful Day in the Neighborhood (2019) and Elvis (2022). He appeared as the title character in the Robert Langdon series and voiced Sheriff Woody in the Toy Story films (1995–2019). Hanks directed and acted in the comedies That Thing You Do! (1996) and Larry Crowne (2011). On television, he had his acting breakthrough starring in the ABC sitcom Bosom Buddies (1980–1982).', 1956, 'tomhanks.jpg', 183);
INSERT INTO public.actors (id, name, bio, year_of_birth, photo_path, height) VALUES (1, 'Francois Cluzet', 'François Cluzet (French pronunciation: [fʁɑ̃swa klyzɛ]; born 21 September 1955) is a French film and theatre actor. Cluzet has collaborated with many important European and American directors, including Claude Chabrol, Bertrand Tavernier, Claire Denis, Agnieszka Holland, Robert Altman and Olivier Assayas. In 2007, he won a French César Award after starring as a doctor suspected of double homicide in the thriller Tell No One (original title Ne le dis à personne). Cluzet may be best known for his role as Philippe in the international hit film The Intouchables (2011).', 1955, 'francoiscluzet.jpg', 174);
INSERT INTO public.actors (id, name, bio, year_of_birth, photo_path, height) VALUES (2, 'Omar Sy', 'Omar Sy (French pronunciation: [ɔmaʁ si], Fula: 𞤌𞤥𞤢𞤪 𞤅𞤭, romanized: Omar Si; born 20 January 1978) is a French actor, best known in France for his sketches with Fred Testot on the Service après-vente des émissions television show on Canal+ (2005–2012). He gained wider recognition for his role in the 2011 comedy-drama film Intouchables, which earned him the César Award for Best Actor, making him the first Black recipient of the award. He later appeared in X-Men: Days of Future Past (2014), Jurassic World (2015), Two Is a Family (2016), Chocolat (2016), Inferno (2016), Transformers: The Last Knight (2017), the Netflix-produced series Lupin (2021–present), Jurassic World Dominion and The Takedown (2022).', 1978, 'omarsy.jpg', 190);
INSERT INTO public.actors (id, name, bio, year_of_birth, photo_path, height) VALUES (3, 'Matthew McConaughey', 'Matthew David McConaughey (/məˈkɒnəheɪ/ mə-KON-ə-hay; born November 4, 1969) is an American actor. He achieved his breakthrough with a supporting performance in the coming-of-age comedy Dazed and Confused (1993). After a number of supporting roles, his first success as a leading man came in the legal drama A Time to Kill (1996). In the 2000s, McConaughey became known for starring in romantic comedies, including How to Lose a Guy in 10 Days (2003), Fool`s Gold (2008), and Ghosts of Girlfriends Past (2009), establishing him as a sex symbol. In 2011, after a two-year hiatus from film acting, McConaughey began to appear in more dramatic roles, beginning with the legal drama The Lincoln Lawyer. In 2012, he gained wider praise for his roles as a stripper in Magic Mike and a fugitive in Mud. McConaughey`s portrayal of Ron Woodroof, a cowboy diagnosed with AIDS, in the biopic Dallas Buyers Club (2013) earned him widespread critical acclaim and numerous accolades, including the Academy Award for Best Actor. He followed it with a supporting role in The Wolf of Wall Street (2013), and a starring role as Rust Cohle in the first season of HBO`s crime anthology series True Detective (2014), for which he was nominated for the Primetime Emmy Award for Outstanding Lead Actor in a Drama Series. His subsequent film roles include starring in Interstellar (2014) and The Gentlemen (2019), as well as voice work in Kubo and the Two Strings (2016), Sing (2016), and Sing 2 (2021).', 1969, 'matthewmcconaughey.jpg', 182);
INSERT INTO public.actors (id, name, bio, year_of_birth, photo_path, height) VALUES (4, 'Anne Hathaway', 'Anne Jacqueline Hathaway (born November 12, 1982) is an American actress. Her accolades include an Academy Award, a British Academy Film Award, a Golden Globe Award, and a Primetime Emmy Award. Her films have grossed over $6.8 billion worldwide, and she appeared on the Forbes Celebrity 100 list in 2009. She was among the world`s highest-paid actresses in 2015. Hathaway performed in several plays in high school. As a teenager, she was cast in the television series Get Real (1999–2000) and made her breakthrough by playing the lead role in the Disney comedy The Princess Diaries (2001). Hathaway had further commercial success in the comedy Get Smart (2008), the romances Bride Wars (2009), Valentine`s Day (2010), and Love & Other Drugs (2010), and the fantasy film Alice in Wonderland (2010). In 2012, she starred as Catwoman in her highest-grossing film, The Dark Knight Rises, and played Fantine, a prostitute dying of tuberculosis, in the musical Les Misérables, winning the Academy Award for Best Supporting Actress for the latter. Hathaway has won a Primetime Emmy Award for her voice role in the sitcom The Simpsons, sung for soundtracks, appeared on stage, and hosted events. She supports several charitable causes. She is a board member of the Lollipop Theatre Network, an organization that brings films to children in hospitals, and advocates for gender equality as a UN Women goodwill ambassador.', 1982, 'annehathaway.jpg', 173);
INSERT INTO public.actors (id, name, bio, year_of_birth, photo_path, height) VALUES (8, 'Michael Clarke Duncan', 'Michael Clarke Duncan (December 10, 1957 – September 3, 2012) was an American actor. He is best known for his breakout role as John Coffey in The Green Mile (1999), for which he was nominated for the Academy Award for Best Supporting Actor and other honors, and for playing Kingpin in Daredevil and Spider-Man: The New Animated Series (both 2003). He also appeared in movies such as Armageddon (1998), The Whole Nine Yards (2000), Planet of the Apes (2001), The Scorpion King (2002), Sin City (2005), and Talladega Nights: The Ballad of Ricky Bobby (2006), as well as in the role of Leo Knox in the television series Bones (2011) and its spin-off The Finder (2012). He also had voice roles in films, including Brother Bear (2003), Kung Fu Panda (2008), and Green Lantern (2011); he had the voice role of Benjamin King in the video game Saints Row (2006).', 1957, 'mclarke.jpg', 196);
INSERT INTO public.actors (id, name, bio, year_of_birth, photo_path, height) VALUES (25, 'Leonardo DiCaprio', 'Leonardo Wilhelm DiCaprio (/diˈkæprioʊ, dɪ-/; Italian: [diˈkaːprjo]; born November 11, 1974) is an American actor and film producer. Known for his work in biographical and period films, he is the recipient of numerous accolades, including an Academy Award, a British Academy Film Award, and three Golden Globe Awards. As of 2019, his films have grossed over $7.2 billion worldwide, and he has been placed eight times in annual rankings of the world''s highest-paid actors. Born in Los Angeles, DiCaprio began his career in the late 1980s by appearing in television commercials. In the early 1990s, he had recurring roles in various television shows, such as the sitcom Parenthood, and had his first major film part as author Tobias Wolff in This Boy''s Life (1993). He later made environmental documentaries and starred in several high-profile directors'' successful projects, including the action thriller Inception (2010), the western Django Unchained (2012), the biopic The Wolf of Wall Street (2013), the survival drama The Revenant (2015)—for which he won the Academy Award for Best Actor— the comedy-dramas Once Upon a Time in Hollywood (2019) and Don''t Look Up (2021), and the crime drama Killers of the Flower Moon (2023).', 1974, 'leoDiCaprio.jpg', 183);
INSERT INTO public.actors (id, name, bio, year_of_birth, photo_path, height) VALUES (26, 'Mark Ruffalo', 'Mark Alan Ruffalo (/ˈrʌfəloʊ/; born November 22, 1967[1]) is an American actor. He began acting in the early 1990s and first gained recognition for his work in Kenneth Lonergan''s play This Is Our Youth (1996) and drama film You Can Count on Me (2000). He went on to star in the romantic comedies 13 Going on 30 (2004), Just like Heaven (2005) and the thrillers In the Cut (2003), Zodiac (2007), and Shutter Island (2010). He received a Tony Award nomination for his supporting role in the Broadway revival of Awake and Sing! in 2006. Ruffalo gained international recognition for playing Bruce Banner / The Hulk since 2012 in the superhero franchise of the Marvel Cinematic Universe.  Ruffalo gained a record-tying four nominations for the Academy Award for Best Supporting Actor for playing a sperm-donor in the comedy-drama The Kids Are All Right (2010), Dave Schultz in the biopic Foxcatcher (2014), Michael Rezendes in the drama Spotlight (2015), and a debauched lawyer in the comic fantasy Poor Things (2023). He won a Screen Actors Guild Award for Best Actor for playing a gay activist in the television drama film The Normal Heart (2015), and a Primetime Emmy Award for Outstanding Lead Actor for his dual role as identical twins in the miniseries I Know This Much Is True (2020).', 1967, 'markRuffalo.jpg', 173);



INSERT INTO public.directors (id, name, bio, date_of_birth, photo_path) VALUES (1, 'Olivier Nakache', 'Olivier Nakache was born on 15 April 1973 in Suresnes, Hauts-de-Seine, France. He is a writer and producer.', '1973-04-15', 'olivierNakache.jpg');
INSERT INTO public.directors (id, name, bio, date_of_birth, photo_path) VALUES (2, 'Christopher Nolan', 'Sir Christopher Edward Nolan CBE (born 30 July 1970) is a British and American filmmaker. Known for his Hollywood blockbusters with complex storytelling, he is considered a leading filmmaker of the 21st century. Nolan`s films have earned over $6.6 billion worldwide, making him the seventh-highest-grossing film director of all time. His accolades include two Academy Awards, a Golden Globe Award and two British Academy Film Awards. Nolan was appointed a Commander of the Order of the British Empire in 2019, and received a knighthood in 2024 for his contributions to film.

Nolan developed an interest in filmmaking from a young age. After studying English literature at University College London, he made several short films before his feature film debut with Following (1998). Nolan gained international recognition with his second film, Memento (2000), and transitioned into studio filmmaking with Insomnia (2002). He became a high-profile director with The Dark Knight trilogy (2005–2012), and found further success with The Prestige (2006), Inception (2010), Interstellar (2014), and Dunkirk (2017). Nolan`s work regularly features in the listings of best films of their respective decades.', '1970-07-30', 'christopherNolan.jpg');
INSERT INTO public.directors (id, name, bio, date_of_birth, photo_path) VALUES (3, 'Frank Darabont', 'Frank Árpád Darabont (born Ferenc Árpád Darabont, January 28, 1959) is an American screenwriter, director and producer.

He has been nominated for three Academy Awards and a Golden Globe Award. In his early career, he was primarily a screenwriter for such horror films as A Nightmare on Elm Street 3: Dream Warriors (1987), The Blob (1988) and The Fly II (1989). As a director, he is known for his film adaptations of Stephen King novellas and novels, such as The Shawshank Redemption (1994), The Green Mile (1999), and The Mist (2007).

Darabont also developed and executive-produced the first season and first half of the second season of the AMC horror drama series The Walking Dead (2010–2011).', '1959-01-28', 'frankDarabont.jpg');
INSERT INTO public.directors (id, name, bio, date_of_birth, photo_path) VALUES (13, 'Martin Scorsese', 'Martin Charles Scorsese (/skɔːrˈsɛsi/ skor-SESS-ee,[1][2] Italian: [skorˈseːze, -se]; born November 17, 1942) is an American filmmaker. He emerged as one of the major figures of the New Hollywood era. He has received many accolades, including an Academy Award, four BAFTA Awards, three Emmy Awards, a Grammy Award and three Golden Globe Awards. He has been honored with the AFI Life Achievement Award in 1997, the Film Society of Lincoln Center tribute in 1998, the Kennedy Center Honor in 2007, Scorsese received a Master of Arts degree from New York University''s Steinhardt School of Culture, Education, and Human Development in 1968. His directorial debut, Who''s That Knocking at My Door (1967), was accepted into the Chicago Film Festival. In the 1970s and 1980s decades, Scorsese''s films, much influenced by his Italian-American background and upbringing in New York City, center on macho-posturing men and explore crime, machismo, nihilism and Catholic concepts of guilt and redemption.[3][4] His trademark styles include extensive use of slow motion and freeze frames, graphic depictions of extreme violence and liberal use of profanity. Mean Streets (1973) was a blueprint for his filmmaking styles. In addition to film, Scorsese has directed episodes for television, including the HBO series Boardwalk Empire (2010–2014) and Vinyl (2016), as well as the HBO documentary Public Speaking (2010) and the Netflix docu-series Pretend It''s a City (2021).', '1942-11-17', 'scorsese.jpg');
INSERT INTO public.directors (id, name, bio, date_of_birth, photo_path) VALUES (19, 'Adwwer bria', '123wdawad', '3123-12-14', '1234.jfif');



INSERT INTO public.flyway_schema_history (installed_rank, version, description, type, script, checksum, installed_by, installed_on, execution_time, success) VALUES (1, '1', 'movie enchanced and new entities', 'SQL', 'V1__movie_enchanced_and_new_entities.sql', 2003174507, 'postgres', '2024-12-05 15:03:34.281053', 31, true);
INSERT INTO public.flyway_schema_history (installed_rank, version, description, type, script, checksum, installed_by, installed_on, execution_time, success) VALUES (2, '2', 'add user table', 'SQL', 'V2__add_user_table.sql', 1243490524, 'postgres', '2024-12-05 15:03:34.338778', 13, true);



INSERT INTO public.genres (id, name, image_path, description) VALUES (1, 'Docudrama', 'docudrama.jpg', 'The docudrama subgenre features dramatized re-enactments of actual events, often from recent history. Docudramas aim to provide a compelling and engaging portrayal of real-life situations while maintaining some of the narrative and emotional elements found in fictional dramas.');
INSERT INTO public.genres (id, name, image_path, description) VALUES (2, 'Comedy', 'comedy.jpg', 'The comedy genre refers to a category of entertainment that aims to amuse and entertain audiences by using humor, wit, and comedic situations. Comedies are created with the primary intention of eliciting laughter and providing lighthearted enjoyment. They encompass a wide range of styles, tones, and themes, appealing to various tastes and audiences.');
INSERT INTO public.genres (id, name, image_path, description) VALUES (3, 'Epic', 'epic.jpg', 'The epic subgenre features grand and sweeping stories often set against significant historical, cultural, or societal backdrops. Epic dramas are characterized by their scope, scale, and often lengthy runtime, as they aim to capture the grandeur of human experiences, events, and emotions.');
INSERT INTO public.genres (id, name, image_path, description) VALUES (4, 'Drama', 'drama.jpg', 'The drama genre is a broad category that features stories portraying human experiences, emotions, conflicts, and relationships in a realistic and emotionally impactful way. Dramas delve into the complexities of human life, often exploring themes of love, loss, morality, societal issues, personal growth, with the aim to evoke an emotional response from the audience by presenting relatable and thought-provoking stories.');
INSERT INTO public.genres (id, name, image_path, description) VALUES (5, 'Tragedy', 'tragedy.jpg', 'The tragedy subgenre features stories characterized by sorrowful events, unfortunate circumstances, and often a sense of inevitable and irreversible loss or downfall. They evoke deep emotions and can explore themes of human suffering, fate, moral dilemmas, and the complexity of human nature.');
INSERT INTO public.genres (id, name, image_path, description) VALUES (23, 'Thriller', 'thriller.jpg', 'All interests Thriller The thriller genre features suspense, tension, and excitement. These stories are known for keeping audiences on the edge of their seats and delivering intense emotional experiences by revolving around high-stakes situations, dangerous conflicts, and the constant anticipation of unexpected events.');


INSERT INTO public.movie_actor (movie_id, actor_id) VALUES (1, 1);
INSERT INTO public.movie_actor (movie_id, actor_id) VALUES (1, 2);
INSERT INTO public.movie_actor (movie_id, actor_id) VALUES (2, 3);
INSERT INTO public.movie_actor (movie_id, actor_id) VALUES (2, 4);
INSERT INTO public.movie_actor (movie_id, actor_id) VALUES (3, 5);
INSERT INTO public.movie_actor (movie_id, actor_id) VALUES (3, 6);
INSERT INTO public.movie_actor (movie_id, actor_id) VALUES (4, 7);
INSERT INTO public.movie_actor (movie_id, actor_id) VALUES (4, 8);
INSERT INTO public.movie_actor (movie_id, actor_id) VALUES (23, 25);
INSERT INTO public.movie_actor (movie_id, actor_id) VALUES (23, 26);




INSERT INTO public.movie_genre (movie_id, genre_id) VALUES (1, 1);
INSERT INTO public.movie_genre (movie_id, genre_id) VALUES (1, 2);
INSERT INTO public.movie_genre (movie_id, genre_id) VALUES (2, 3);
INSERT INTO public.movie_genre (movie_id, genre_id) VALUES (2, 4);
INSERT INTO public.movie_genre (movie_id, genre_id) VALUES (3, 3);
INSERT INTO public.movie_genre (movie_id, genre_id) VALUES (3, 4);
INSERT INTO public.movie_genre (movie_id, genre_id) VALUES (4, 4);
INSERT INTO public.movie_genre (movie_id, genre_id) VALUES (4, 5);
INSERT INTO public.movie_genre (movie_id, genre_id) VALUES (23, 4);
INSERT INTO public.movie_genre (movie_id, genre_id) VALUES (23, 23);




INSERT INTO public.movies (id, title, description, country, director_id, release_date, poster_path, length, budget, box_office) VALUES (1, 'Intouchables', 'After he becomes a quadriplegic from a paragliding accident, an aristocrat hires a young man from the projects to be his caregiver.', 'France', 1, '2011-11-02', 'Intouchables.jpg', 112, 10800000, 426000000);
INSERT INTO public.movies (id, title, description, country, director_id, release_date, poster_path, length, budget, box_office) VALUES (2, 'Interstellar', 'When Earth becomes uninhabitable in the future, a farmer and ex-NASA pilot, Joseph Cooper, is tasked to pilot a spacecraft, along with a team of researchers, to find a new planet for humans.', 'USA', 2, '2014-11-07', 'Interstellar.jpg', 169, 165000000, 730800000);
INSERT INTO public.movies (id, title, description, country, director_id, release_date, poster_path, length, budget, box_office) VALUES (3, 'The Shawshank Redemption', 'A banker convicted of uxoricide forms a friendship over a quarter century with a hardened convict, while maintaining his innocence and trying to remain hopeful through simple compassion.', 'USA', 3, '2014-10-14', 'Shawshank.jpg', 142, 25000000, 73300000);
INSERT INTO public.movies (id, title, description, country, director_id, release_date, poster_path, length, budget, box_office) VALUES (4, 'The Green Mile', 'Paul Edgecomb, the head guard of a prison, meets an inmate, John Coffey, a black man who is accused of murdering two girls. His life changes drastically when he discovers that John has a special gift.', 'USA', 3, '1999-12-10', 'Mile.jpg', 189, 60000000, 286800000);
INSERT INTO public.movies (id, title, description, country, director_id, release_date, poster_path, length, budget, box_office) VALUES (23, 'Shuttered Island', 'Two US marshals are sent to a mental institution on an inhospitable island in order to investigate the disappearance of a patient.', 'USA', 13, '2010-02-19', 'shutterIsland.jpg', 138, 80000000, 294000000);



INSERT INTO public.ratings (id, user_id, movie_id, rating) VALUES (6, 19, 2, 88);
INSERT INTO public.ratings (id, user_id, movie_id, rating) VALUES (7, 19, 3, 76);
INSERT INTO public.ratings (id, user_id, movie_id, rating) VALUES (8, 19, 4, 76);
INSERT INTO public.ratings (id, user_id, movie_id, rating) VALUES (9, 19, 23, 97);
INSERT INTO public.ratings (id, user_id, movie_id, rating) VALUES (10, 16, 1, 67);
INSERT INTO public.ratings (id, user_id, movie_id, rating) VALUES (11, 16, 2, 76);
INSERT INTO public.ratings (id, user_id, movie_id, rating) VALUES (12, 16, 3, 66);
INSERT INTO public.ratings (id, user_id, movie_id, rating) VALUES (13, 16, 4, 80);
INSERT INTO public.ratings (id, user_id, movie_id, rating) VALUES (14, 16, 23, 75);
INSERT INTO public.ratings (id, user_id, movie_id, rating) VALUES (15, 17, 1, 87);
INSERT INTO public.ratings (id, user_id, movie_id, rating) VALUES (16, 17, 2, 76);
INSERT INTO public.ratings (id, user_id, movie_id, rating) VALUES (17, 17, 3, 98);
INSERT INTO public.ratings (id, user_id, movie_id, rating) VALUES (18, 17, 4, 87);
INSERT INTO public.ratings (id, user_id, movie_id, rating) VALUES (19, 17, 23, 89);
INSERT INTO public.ratings (id, user_id, movie_id, rating) VALUES (20, 20, 1, 80);
INSERT INTO public.ratings (id, user_id, movie_id, rating) VALUES (5, 19, 1, 77);



INSERT INTO public.reviews (id, content, created_at, user_id, movie_id) VALUES (42, 'It''s a great movie! Watched it with the whole family!', '2024-12-12 17:10:17.268169', 19, 1);
INSERT INTO public.reviews (id, content, created_at, user_id, movie_id) VALUES (43, 'It''s a great movie! Watched it with the whole family!', '2024-12-12 17:10:38.204893', 19, 2);
INSERT INTO public.reviews (id, content, created_at, user_id, movie_id) VALUES (44, 'It''s a great movie! Watched it with the whole family!', '2024-12-12 17:10:53.346893', 19, 4);
INSERT INTO public.reviews (id, content, created_at, user_id, movie_id) VALUES (45, 'It''s a great movie! Watched it with the whole family!', '2024-12-12 17:11:12.853895', 19, 23);
INSERT INTO public.reviews (id, content, created_at, user_id, movie_id) VALUES (46, 'Not a bad movie for a night out', '2024-12-12 17:11:51.623936', 16, 1);
INSERT INTO public.reviews (id, content, created_at, user_id, movie_id) VALUES (47, 'Not a bad movie for a night out', '2024-12-12 17:12:02.390936', 16, 2);
INSERT INTO public.reviews (id, content, created_at, user_id, movie_id) VALUES (48, 'Not a bad movie for a night out', '2024-12-12 17:12:10.2907', 16, 3);
INSERT INTO public.reviews (id, content, created_at, user_id, movie_id) VALUES (49, 'Not a bad movie for a night out', '2024-12-12 17:12:20.642491', 16, 4);
INSERT INTO public.reviews (id, content, created_at, user_id, movie_id) VALUES (50, 'Not a bad movie for a night out', '2024-12-12 17:12:37.775496', 16, 23);
INSERT INTO public.reviews (id, content, created_at, user_id, movie_id) VALUES (51, 'It''s the best movie I''ve ever seen. I recommend it', '2024-12-12 17:13:34.696118', 17, 1);
INSERT INTO public.reviews (id, content, created_at, user_id, movie_id) VALUES (52, 'It''s the best movie I''ve ever seen. I recommend it', '2024-12-12 17:13:47.43912', 17, 2);
INSERT INTO public.reviews (id, content, created_at, user_id, movie_id) VALUES (53, 'It''s the best movie I''ve ever seen. I recommend it', '2024-12-12 17:13:55.00112', 17, 3);
INSERT INTO public.reviews (id, content, created_at, user_id, movie_id) VALUES (54, 'It''s the best movie I''ve ever seen. I recommend it', '2024-12-12 17:14:04.457118', 17, 4);
INSERT INTO public.reviews (id, content, created_at, user_id, movie_id) VALUES (55, 'It''s the best movie I''ve ever seen. I recommend it', '2024-12-12 17:14:13.958601', 17, 23);
INSERT INTO public.reviews (id, content, created_at, user_id, movie_id) VALUES (56, 'It''s a great movie! Watched it with the whole family!', '2024-12-12 17:14:45.655599', 19, 3);
INSERT INTO public.reviews (id, content, created_at, user_id, movie_id) VALUES (57, 'Mama', '2024-12-12 23:55:13.344327', 19, 1);



INSERT INTO public.users (id, username, password, avatar_path, role) VALUES (16, 'customer', '$2a$12$PqJAi37HIDopaw5n5XTibe2gpi1.sPGdG9bXxXddqxOy0RqpYJZcS', 'goroh.png', 'CUSTOMER');
INSERT INTO public.users (id, username, password, avatar_path, role) VALUES (17, 'moderator', '$2a$12$J7DtPvRQHCd4qkj9NyQZg.DuiSiI//tb1ywCPzc/mkldPWXK2yxOe', 'zombieCute.png', 'MODERATOR');
INSERT INTO public.users (id, username, password, avatar_path, role) VALUES (19, 'admin', '$2a$12$FGAzZgrJWN1evdUCnEdN8.wBMweN6K./L8tEe.CSGMgFxl0A6RTSq', 'zombiePunk.png', 'ADMIN');
INSERT INTO public.users (id, username, password, avatar_path, role) VALUES (20, 'Antiangel2007', '$2a$12$NEgvnYt50b93jf7LevwgauXEpB.BlVgng1iWCg6pSMuDDyi/LwXUO', 'goroh.png', 'CUSTOMER');




SELECT pg_catalog.setval('public.actors_id_seq', 28, true);


SELECT pg_catalog.setval('public.directors_id_seq', 19, true);




SELECT pg_catalog.setval('public.genres_id_seq', 26, true);



SELECT pg_catalog.setval('public.movies_id_seq', 24, true);



SELECT pg_catalog.setval('public.ratings_id_seq', 20, true);




SELECT pg_catalog.setval('public.reviews_id_seq', 57, true);



SELECT pg_catalog.setval('public.users_id_seq', 20, true);




ALTER TABLE ONLY public.actors
    ADD CONSTRAINT actors_name_key UNIQUE (name);




ALTER TABLE ONLY public.actors
    ADD CONSTRAINT actors_pkey PRIMARY KEY (id);



ALTER TABLE ONLY public.directors
    ADD CONSTRAINT directors_name_key UNIQUE (name);




ALTER TABLE ONLY public.directors
    ADD CONSTRAINT directors_pkey PRIMARY KEY (id);


ALTER TABLE ONLY public.flyway_schema_history
    ADD CONSTRAINT flyway_schema_history_pk PRIMARY KEY (installed_rank);



ALTER TABLE ONLY public.genres
    ADD CONSTRAINT genres_name_key UNIQUE (name);




ALTER TABLE ONLY public.genres
    ADD CONSTRAINT genres_pkey PRIMARY KEY (id);




ALTER TABLE ONLY public.movie_actor
    ADD CONSTRAINT movie_actor_pkey PRIMARY KEY (movie_id, actor_id);




ALTER TABLE ONLY public.movie_genre
    ADD CONSTRAINT movie_genre_pkey PRIMARY KEY (movie_id, genre_id);




ALTER TABLE ONLY public.movies
    ADD CONSTRAINT movies_pkey PRIMARY KEY (id);



ALTER TABLE ONLY public.ratings
    ADD CONSTRAINT ratings_pkey PRIMARY KEY (id);




ALTER TABLE ONLY public.reviews
    ADD CONSTRAINT reviews_pkey PRIMARY KEY (id);




ALTER TABLE ONLY public.ratings
    ADD CONSTRAINT unique_user_movie UNIQUE (user_id, movie_id);


ALTER TABLE ONLY public.users
    ADD CONSTRAINT users_pkey PRIMARY KEY (id);



ALTER TABLE ONLY public.users
    ADD CONSTRAINT users_username_key UNIQUE (username);




CREATE INDEX flyway_schema_history_s_idx ON public.flyway_schema_history USING btree (success);




ALTER TABLE ONLY public.ratings
    ADD CONSTRAINT fk_movie FOREIGN KEY (movie_id) REFERENCES public.movies(id) ON DELETE CASCADE;



ALTER TABLE ONLY public.ratings
    ADD CONSTRAINT fk_user FOREIGN KEY (user_id) REFERENCES public.users(id) ON DELETE CASCADE;



ALTER TABLE ONLY public.movie_actor
    ADD CONSTRAINT movie_actor_actor_id_fkey FOREIGN KEY (actor_id) REFERENCES public.actors(id);



ALTER TABLE ONLY public.movie_actor
    ADD CONSTRAINT movie_actor_movie_id_fkey FOREIGN KEY (movie_id) REFERENCES public.movies(id) ON DELETE CASCADE;



ALTER TABLE ONLY public.movie_genre
    ADD CONSTRAINT movie_genre_genre_id_fkey FOREIGN KEY (genre_id) REFERENCES public.genres(id);




ALTER TABLE ONLY public.movie_genre
    ADD CONSTRAINT movie_genre_movie_id_fkey FOREIGN KEY (movie_id) REFERENCES public.movies(id) ON DELETE CASCADE;



ALTER TABLE ONLY public.movies
    ADD CONSTRAINT movies_director_id_fkey FOREIGN KEY (director_id) REFERENCES public.directors(id) ON DELETE CASCADE;




ALTER TABLE ONLY public.reviews
    ADD CONSTRAINT reviews_movie_id_fkey FOREIGN KEY (movie_id) REFERENCES public.movies(id);



ALTER TABLE ONLY public.reviews
    ADD CONSTRAINT reviews_user_id_fkey FOREIGN KEY (user_id) REFERENCES public.users(id);




REVOKE USAGE ON SCHEMA public FROM PUBLIC;
