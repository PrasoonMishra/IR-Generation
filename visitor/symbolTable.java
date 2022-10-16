package visitor;
import java.util.ArrayList;
import java.util.Map;

public class symbolTable{
	public Map<String, Map<String, Map<String,String>>> Table;
	public Map<String,Map<String,ArrayList<String>>> SignTable;
	public Map<String,ArrayList<String>> Tree;
	symbolTable(Map<String, Map<String, Map<String,String>>> Table , Map<String,Map<String,ArrayList<String>>> SignTable, Map<String,ArrayList<String>> Tree){
		this.Table = Table;
		this.SignTable = SignTable;
		this.Tree = Tree;
	}
	public void terminate(String e) {
		//System.out.println(e);
		System.out.println("Does not Type Check");
		System.exit(0);
	}
	public String match(String c,String m,ArrayList<String> p) {
		ArrayList<String> temp1 = null;
		int it1;
		if(this.SignTable.containsKey(c)) {
			if(this.SignTable.get(c).containsKey(m)) {
				temp1 = this.SignTable.get(c).get(m);
				if(p.size()==temp1.size()-1) {
					for(it1=1;it1<temp1.size();it1++) {
						if(!(temp1.get(it1).equals(p.get(it1-1)) || this.isSubclass(temp1.get(it1),p.get(it1-1))))
							break;
					}
					if(it1<temp1.size()) {
						this.terminate("match - param type not matching");
					}
						
				}
				else {
					this.terminate("match - no of param not matching");
				}
			}
			else {
				this.terminate("match - no such method");
			}
		}
		else {
			this.terminate("match - no such class");
		}
		return(temp1.get(0));
	}
	
	public boolean isSubclass(String parent,String child) {
		if(!(this.Table.containsKey(parent) && this.Table.containsKey(child)))
			return false;
		ArrayList<String> dfs = new ArrayList<String>();
		ArrayList<String> buffer;
		String current;
		int i;
		dfs.add(parent);
		while(dfs.size()>0) {
			current = dfs.get(dfs.size()-1);
			dfs.remove(dfs.size()-1);
			if(this.Tree.containsKey(current)) {
				buffer = this.Tree.get(current);
				for(i=0;i<buffer.size();i++) {
					if(buffer.get(i).equals(child)) {
						return true;
					}
					else {
						dfs.add(buffer.get(i));
					}
				}
			}	
		}
		return false;
	}
}