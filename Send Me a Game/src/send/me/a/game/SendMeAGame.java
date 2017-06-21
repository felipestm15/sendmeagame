package send.me.a.game;

import java.util.List;
import java.util.Random;

import com.pengrad.telegrambot.TelegramBot;
import com.pengrad.telegrambot.TelegramBotAdapter;
import com.pengrad.telegrambot.model.Update;
import com.pengrad.telegrambot.model.request.ChatAction;
import com.pengrad.telegrambot.request.GetUpdates;
import com.pengrad.telegrambot.request.SendChatAction;
import com.pengrad.telegrambot.request.SendMessage;
import com.pengrad.telegrambot.response.BaseResponse;
import com.pengrad.telegrambot.response.GetUpdatesResponse;
import com.pengrad.telegrambot.response.SendResponse;

public class SendMeAGame {

	public static void main(String[] args) {
		
		

		//Criação do objeto bot com as informações de acesso
		TelegramBot bot = TelegramBotAdapter.build("319000252:AAF6E_H746gvnXMfBwqj9nIInZ1cI-skhik");

		//objeto responsável por receber as mensagens
		GetUpdatesResponse updatesResponse;
		//objeto responsável por gerenciar o envio de respostas
		SendResponse sendResponse;
		//objeto responsável por gerenciar o envio de ações do chat
		BaseResponse baseResponse;
		
		//controle de off-set, isto é, a partir deste ID será lido as mensagens pendentes na fila
		int m=0;
		
		//loop infinito pode ser alterado por algum timer de intervalo curto
		while (true){
		
			//executa comando no Telegram para obter as mensagens pendentes a partir de um off-set (limite inicial)
			updatesResponse =  bot.execute(new GetUpdates().limit(100).offset(m));
			
			//lista de mensagens
			List<Update> updates = updatesResponse.updates();

			//análise de cada ação da mensagem
			for (Update update : updates) {
				
				//atualização do off-set
				m = update.updateId()+1;
				String mensagem = update.message().text();
                String start = "/start";
                
                
                
				System.out.println("Recebendo mensagem:"+ update.message());
				
				if(mensagem.equals("/start")){
					//envio de "Escrevendo" antes de enviar a resposta
					baseResponse = bot.execute(new SendChatAction(update.message().chat().id(), ChatAction.typing.name()));
					//verificação de ação de chat foi enviada com sucesso
					System.out.println("Resposta de Chat Action Enviada?" + baseResponse.isOk());
					
					//envio da mensagem de resposta
                    sendResponse = bot.execute(new SendMessage(update.message().chat().id(),"COMO USAR O BOT: \n================\n"
                            + "Digite /send jogo.\n\n"                           
                            + "E então o bot lhe enviará um jogo de uma lista aleatória, também lhe enviará uma imagem do mesmo e um pouco de informação sobre ele.\n\n"));
					//verificação de mensagem enviada com sucesso
					System.out.println("Mensagem Enviada?" +sendResponse.isOk());
				}
				else if(mensagem.equals("/send jogo")){
					String lista[] = {"O jogo de hoje será League of Legends.\nLeague of Legends é um jogo eletrônico do gênero multiplayer online battle arena, desenvolvido e publicado pela Riot Games para Microsoft Windows e Mac OS X lançado em 27 de outubro de 2009 sendo um dos jogos mais jogados até hoje.\n", 
                                                        "O jogo de hoje será Overwatch.\nOverwatch é um jogo eletrônico multiplayer de tiro em primeira pessoa desenvolvido pela Blizzard Entertainment e eleito jogo do ano de 2016.\n",
                                                        "O jogo de hoje será Counter Strike Global Offensive.\nCounter-Strike: Global Offensive é um jogo de tiro em primeira pessoa desenvolvido pela Valve Corporation e pela Hidden Path Entertainment, sendo uma sêquencia de Counter-Strike: Source. É o quarto titulo principal da franquia.\n", 
                                                        "O jogo de hoje será Battlefield 1.\nBattlefield 1 é um jogo eletrônico de tiro em primeira pessoa ambientado na Primeira Guerra Mundial, desenvolvido pela EA DICE e publicada pela Eletronics Arts. É o décimo quarto jogo da franquia Battlefield.\n", 
							"O jogo de hoje será Minecraft.\nMinecraft é um jogo eletrônico tipo sandbox e independente de mundo aberto que permite a construção usando blocos dos quais o mundo é feito. Foi criado por Markus 'Notch' Persson.\n",
                                                        "O jogo de hoje será The Witcher.\nThe Witcher é uma aclamada série de jogos eletrônicos estabelecida em 2007 pela desenvolvedora polonesa CD Projekt RED que baseia-se na série de contos e romances Wiedźmin, do escritor Andrzej Sapkowski.\n",
                                                        "O Jogo de hoje será Call of Duty: Black Ops II.\nCall of Duty: Black Ops II é um videojogo de tiro em primeira pessoa, desenvolvido pela Treyarch e publicado pela Activision.\n",
                                                        "O jogo de hoje será GTA V.\nGrand Theft Auto V é um jogo eletrônico de ação-aventura desenvolvido pela Rockstar North e publicado pela Rockstar Games.\n",
                                                        "O Jogo de hoje será Dota 2.\nDota 2 é um jogo eletrônico do gênero Action Real-Time Strategy (abrevidado como ARTS) ou também considerado do gênero Multiplayer Online Battle Arena (abreviado como MOBA), foi desenvolvido pela Valve Corporation como sequência do Defense of the Ancients (DotA), uma modificação (mod) em um mapa desenvolvido para World of warcraft.\n",
                                                        "O Jogo de hoje será Gwent: The Witcher Card Game.\nGwent: The Witcher Card Game é um futuro jogo eletrônico free-to-play de cartas colecionáveis que está sendo desenvolvido pela CD Projekt RED para as plataformas PlayStation 4, Xbox One e Microsoft Windows.\n", 
                                                        "O Jogo de hoje será Fifa 2017.\nFIFA 17 é um jogo eletrônico de futebol desenvolvido e publicado pela EA Sports, que foi lançado mundialmente em 27 de setembro de 2016.\n",
                                                        "O Jogo de hoje será PES 2017.\nPro Evolution Soccer 2017, é um jogo de futebol desenvolvido pela Konami para PlayStation 4, Xbox One, Xbox 360, Playstation 3, PC, iOS e Android que pertence à série Pro Evolution Soccer.\n", 
                                                        "O Jogo de hoje será Heroes of the Storm.\nHeroes of the Storm é um jogo eletrônico de multiplayer online desenvolvido pela Blizzard Entertainment para as plataformas Microsoft Windows e OS X.\n", 
                                                        "O Jogo de hoje será Diablo III.\nDiablo III é um RPG de ação desenvolvido pela Blizzard Entertainment, o terceiro título da série Diablo. Sua produção foi revelada em 28 de junho de 2008, na Blizzard Entertainment Worldwide Invitational.\n", 
                                                        "O Jogo de hoje será Dark Souls 3.\nDark Souls III, é um jogo do género role-playing game de ação, o quarto da série Souls, desenvolvido pela From Software e co-realizado por Hidetaka Miyazaki o criador da série.\n"};
					String lista2[] = {"https://games.openmandriva.org/br/wp-content/uploads/2015/09/lol.png",
                                            "http://gameranx.com/wp-content/uploads/2016/11/overwatch-heroes-background-blizzard-1080x623.png",
                                            "https://steamuserimages-a.akamaihd.net/ugc/572313256310941553/1F5464CF2BB3613C5179DB552AEF86473D2F3130/",
                                            "http://gamingnews.com.br/wp-content/uploads/1462625407-12810-Electronic-Arts-Inc-Battlefield-1-Classes-Revealed-Features-Dedicated-Vehicle-Classes-1.jpg",
                                            "http://minecraftboss.com/wp-content/uploads/2016/08/Minecraft-Free-Download-PC-Mac.jpg",
                                            "https://staticdelivery.nexusmods.com/mods/952/images/1709-1-1466385089.jpg",
                                            "https://i.ytimg.com/vi/VdsKartkM0o/maxresdefault.jpg",
                                            "https://i.ytimg.com/vi/jD_DxgB9Beg/maxresdefault.jpg",
                                            "http://sm.ign.com/ign_br/screenshot/default/dota-2-official_wdfp.jpg",
                                            "http://overloadr.com.br/wp-content/uploads/2017/02/xS6ksjrknbjq3Mtre6RZmQ.jpg",
                                            "https://cdn2.hubspot.net/hubfs/459815/fifa-2017-confira-os-melhores-meias-atacantes-ou-mei-do-game.png",
                                            "http://www.pesfanportugal.com/wp-content/uploads/2016/12/pes2017andy-1024x576.jpg",
                                            "http://apptrigger.com/files/2016/05/heroes-facebook-preview.jpg",
                                            "https://www.gameladen.com/media/catalog/product/d/i/diablo3_screenshot1.jpg",
                                            "https://i.ytimg.com/vi/DSxuZr46_bc/maxresdefault.jpg",};
					Random random = new Random();
					int jogo = 0;
									
					for(int i = 0; i < 10; i++){
						jogo = random.nextInt(14);
					}
					String game = lista[jogo];
					String gif = lista2[jogo];
                                        
					
                    //envio de "Escrevendo" antes de enviar a resposta
                    baseResponse = bot.execute(new SendChatAction(update.message().chat().id(), ChatAction.typing.name()));
                    //verificação de ação de chat foi enviada com sucesso
                    System.out.println("Resposta de Chat Action Enviada?" + baseResponse.isOk());

                    //envio da mensagem de resposta
                    sendResponse = bot.execute(new SendMessage(update.message().chat().id(),game
                    		+ " " + gif));
                    //verificação de mensagem enviada com sucesso
                    System.out.println("Mensagem Enviada?" +sendResponse.isOk());
				}
				else{
                    //envio de "Escrevendo" antes de enviar a resposta
                    baseResponse = bot.execute(new SendChatAction(update.message().chat().id(), ChatAction.typing.name()));
                    //verificação de ação de chat foi enviada com sucesso
                    System.out.println("Resposta de Chat Action Enviada?" + baseResponse.isOk());

                    //envio da mensagem de resposta
                    sendResponse = bot.execute(new SendMessage(update.message().chat().id(),"Algo de errado aconteceu :("));
                    //verificação de mensagem enviada com sucesso
                    System.out.println("Mensagem Enviada?" +sendResponse.isOk());
				}
				
				
			}

		}

	}

}