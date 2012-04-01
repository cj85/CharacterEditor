import java.util.ArrayList;

public class CreatFighterTest{
	public static void main(String[] args){
		int i=1;
		
		switch(i){
		case 1:{//
			Fighter fighter=new Fighter("first fighter");
			
			ArrayList<Head> headlist=HeadParser.parse();
			Head head=headlist.get(1);
			fighter.setHead(head.getName());
			
			ArrayList<Fighter> fightertowrite=new ArrayList<Fighter>();
			fightertowrite.add(fighter);
			WriteFighterFile writer =new WriteFighterFile(fightertowrite);
			writer.creat();
			break;
			}
		case 2:{//test HeadParser and HeadWriter
			ArrayList<Head> headlist=HeadParser.parse();
			for (Head h: headlist)
				if(h.getName().equalsIgnoreCase("second"))
					h.setProperty("big");
			WriteHeadFile writer=new WriteHeadFile(headlist);
			writer.creat();
			break;
			}
		case 3:{//test WriteHeadFile
			ArrayList<Head> headlist=new ArrayList<Head>();
			Head head1=new Head("first");
			head1.setProperty("default");
			Head head2=new Head("second");
			head2.setProperty("default");
			headlist.add(head1);
			headlist.add(head2);
			WriteHeadFile writer=new WriteHeadFile(headlist);
			writer.creat();
			break;
		}
		case 4:{//test FighterParser
			ArrayList<Fighter> fighterlist=FighterParser.parse();
			Fighter fighter=new Fighter("second fighter");
			fighter.setHead("small");
			fighterlist.add(fighter);
			
			WriteFighterFile writer=new WriteFighterFile(fighterlist);
			writer.creat();
			break;
		}
		}
		

	}
}