INSERT [dbo].[Categories] VALUES ( N'CONCERTS', N'http://dezbateri.ro/pic/co.jpg')
INSERT [dbo].[Categories] VALUES ( N'FESTIVALS', N'http://dezbateri.ro/pic/fe.jpg')
INSERT [dbo].[Categories] VALUES ( N'SPORTS', N'http://dezbateri.ro/pic/sp.jpg')
INSERT [dbo].[Categories] VALUES ( N'THEATRE, OPERA', N'http://dezbateri.ro/pic/th.jpg')
INSERT [dbo].[Categories] VALUES ( N'CINEMA', N'http://dezbateri.ro/pic/ci.jpg')
INSERT [dbo].[Categories] VALUES ( N'PARTIES', N'http://dezbateri.ro/pic/pa.jpg')
INSERT [dbo].[Categories] VALUES ( N'SOCIAL', N'http://dezbateri.ro/pic/social.jpg')

INSERT [dbo].[Roles] VALUES ( N'regular')
INSERT [dbo].[Roles] VALUES ( N'admin')
INSERT [dbo].[Roles] VALUES ( N'vip')


INSERT [dbo].[Users]  VALUES ( N'User Theuser', N'myuser', N'parola', N'user@gmail.com', N'28/01/2000', N'https://s-media-cache-ak0.pinimg.com/736x/76/22/5b/76225b3c2d672b5ddb6afc0bc5724488--rainy-days-rainy-weather.jpg', 1000)
INSERT [dbo].[Users]  VALUES ( N'Bogdan Stupariu', N'bogdanst', N'parola', N'bogdan@yahoo.com', N'28/07/1956', N'https://s-media-cache-ak0.pinimg.com/736x/76/22/5b/76225b3c2d672b5ddb6afc0bc5724488--rainy-days-rainy-weather.jpg', 1000)
INSERT [dbo].[Users]  VALUES ( N'Daniela Aniela', N'bogdan', N'parola', N'bogdan@yah.com', N'06/07/1994', N'https://s-media-cache-ak0.pinimg.com/736x/76/22/5b/76225b3c2d672b5ddb6afc0bc5724488--rainy-days-rainy-weather.jpg', 1000)


INSERT [dbo].[Events] VALUES ( N'FIBA EuroBasket 2017', N'Campionatul European de baschet masculin Eurobasket din 2017, din care o grupă va fi găzduită de Cluj ', N'Cluj, Sala Polivalenta', N'14:00', N'31/08/2017', N'09/09/2017', N'4', 200, 150, N'going', 1001, 1009)
INSERT [dbo].[Events] VALUES ( N'Some sport event', N'This is a description lorem ipsum loret doloret something in latin', N'Bucharest, nowere', N'20:00', N'29/09/2017', N'29/09/2017', N'12', 100, 129 ,N'going', 1001, 1009)
INSERT [dbo].[Events] VALUES ( N'Untold festival', N'Discover the Untold Story. 4 days and 4 nights full of magic, sound and image. Featuring some of the best artists in the world.   În universul plin de magie UNTOLD apare în 2017, una din cele mai puternice constelații formată din 7 superstaruri ale stilului live act. Pe UNTOLD ARENA vor urca în această vară Ellie Goulding, Hurts, MØ, R City, Tinie Tempah, Example & DJ Wire și Jasmine Thompson. Cei 7 artiști vin să completeze line-up-ul impresionant al UNTOLD 2017, anunțat deja: Armin van Buuren, Afrojack, Axwell&Ingrosso, Dimitri Vegas&Like Mike, Hardwell, Martin Garrix, Steve Aoki, Chris Liebing, Loco Dice, Solomun, Sven Vath, Jamie Jones, Dubfire,  Andy C, Borgore, Chase&Status(DJ Set), Dub FX, Pendulum(DJ Set) și Sigma(DJ Set).', N'Cluj, Cluj Arena', N'13:00', N'03/08/2017', N'06/08/2017', N'18', 12000, 255, N'going', 1002, 1008)
INSERT [dbo].[Events] VALUES ( N'Summer Well 2017 ', N'Orange prezintă a VII-a ediție a festivalului Summer Well, care va avea loc pe 12 și 13 august, pe Domeniul Știrbey din Buftea.', N'Buftea, domeniul Stirbei', N'12:00', N'12/08/2017', N'13/08/2017', N'14', 5000, 129, N'going', 1002, 1008)
INSERT [dbo].[Events] VALUES ( N'Richard Clayderman', N'Richard Clayderman prețuieste Femeia printr-un concert de proporții programat în 2018', N'Bucuresti, Sala Palatului', N'20:00', N'08/03/2017', N'08/03/2017', N'1', 1000, 25, N'going', 1003, 1007)
INSERT [dbo].[Events] VALUES ( N'Vama-lansare de album', N'VAMA lansează noul album printr-un concert la Sala Polivalentă București', N'Bucuresti, Sala Polivalenta', N'20:00', N'12/10/2017', N'12/10/2017', N'12', 10, 49,N'going', 1001, 1007)
INSERT [dbo].[Events] VALUES ( N'THE DIRE STRAITS EXPERIENCE ', N'Dire Straits Experience aniversează 40 de ani de la lansarea “Sultans of Swing” prin 3 concerte în România', N'Bucuresti, Sala Palatului', N'20:00', N'11/12/2017', N'11/12/2017', N'10', 77, 10000, N'going', 1003, 1007)

INSERT [dbo].[Pictures]  VALUES ( N'http://dezbateri.ro/pic/e/fiba.jpg', 1007)
INSERT [dbo].[Pictures]  VALUES ( N'http://dezbateri.ro/pic/e/sport.jpg', 1008)
INSERT [dbo].[Pictures]  VALUES ( N'http://dezbateri.ro/pic/e/unt.jpg', 1009)
INSERT [dbo].[Pictures]  VALUES ( N'http://dezbateri.ro/pic/e/sum.jpg', 1010)
INSERT [dbo].[Pictures]  VALUES ( N'http://dezbateri.ro/pic/e/clay.jpg', 1011)
INSERT [dbo].[Pictures]  VALUES ( N'http://dezbateri.ro/pic/e/vama.jpg', 1012)
INSERT [dbo].[Pictures]  VALUES ( N'http://dezbateri.ro/pic/e/dire.jpg', 1013)


