

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Scanner;

public class GraphicalModel {

	// ****************************************************************************************************************//
	// Variables of Graphical Model //

	// modelType variable, get & set functions
	private String modelType;

	public String getModelType() {
		return modelType;
	}

	public void setModelType(String modelType) {
		this.modelType = modelType;
	}

	// Number of Nodes, get & set functions
	private int numberOfNodes;

	public int getNumberOfNodes() {
		return numberOfNodes;
	}

	public void setNumberOfNodes(int numberOfNodes) {
		this.numberOfNodes = numberOfNodes;
	}
	
	// Number of evidence Nodes
	private int numberOfEvidances;
	
	public int getNumberOfEvidances() {
		return numberOfEvidances;
	}

	public void setNumberOfEvidances(int numberOfEvidances) {
		this.numberOfEvidances = numberOfEvidances;
	}

	// Number of functions, get & set functions
	private int numberOfFunctions;

	public int getNumberOfFunctions() {
		return numberOfFunctions;
	}

	public void setNumberOfFunctions(int numberOfFunctions) {
		this.numberOfFunctions = numberOfFunctions;
	}

	// Array of nodes
	private Node[] nodeArray;

	public Node[] getNodeArray() {
		return nodeArray;
	}

	public void setNodeArray(Node[] nodeArray) {
		this.nodeArray = nodeArray;
	}

	// Array of functions
	private Function[] functionArray;

	public Function[] getFunctionArray() {
		return functionArray;
	}

	public void setFunctionArray(Function[] functionArray) {
		this.functionArray = functionArray;
	}

	// End of Graphical Model Variables //
	// ***************************************************************************************************************//

	// ***************************************************************************************************************//
	// Node Class //

	public class Node implements Comparable<Node> {

		// nodeNumber variable, get & set functions
		private int nodeNumber;

		public int getNodeNumber() {
			return nodeNumber;
		}

		public void setNodeNumber(int nodeNumber) {
			this.nodeNumber = nodeNumber;
		}

		// domain size of the node, get & set functions
		private int domainSize;

		public int getDomainSize() {
			return domainSize;
		}

		public void setDomainSize(int domainSize) {
			this.domainSize = domainSize;
		}

		// nodes adjacent to this node variable, get & set functions
		private HashSet<Integer> adjacentNodes;

		// adjacent Node count, get & set functions
		private int adjacentNodeCount;

		public int getAdjacentNodeCount() {
			return adjacentNodeCount;
		}

		public void setAdjacentNodeCount(int adjacentNodeCount) {
			this.adjacentNodeCount = adjacentNodeCount;
		}

		// functions related to node
		private int[] functions;

		public int[] getFunctions() {
			return functions;
		}

		public void setFunctions(int[] functions) {
			this.functions = functions;
		}

		// number of functions
		private int numberOfFunctions;

		public int getNumberOfFunctions() {
			return numberOfFunctions;
		}

		public void setNumberOfFunctions(int numberOfFunctions) {
			this.numberOfFunctions = numberOfFunctions;
		}

		// Evidence
		private boolean evidence;

		public boolean isEvidence() {
			return evidence;
		}

		public void setEvidence(boolean evidence) {
			this.evidence = evidence;
		}

		// Evidence value
		private int evidenceValue;

		public int getEvidenceValue() {
			return evidenceValue;
		}

		public void setEvidenceValue(int evidenceValue) {
			this.evidenceValue = evidenceValue;
		}

		// Comparable function over node
		@Override
		public int compareTo(Node n) {
			// TODO Auto-generated method stub
			if (this.adjacentNodeCount < n.adjacentNodeCount) {
				return -1;
			} else if (this.adjacentNodeCount > n.adjacentNodeCount) {
				return 1;
			} else {
				return 0;
			}
		}

	}

	// End of Node Class //
	// ****************************************************************************************************************//

	
	// ****************************************************************************************************************//
	// Function Class //

	public class Function {
		// functionNumber
		private int functionNumber;

		public int getFunctionNumber() {
			return functionNumber;
		}

		public void setFunctionNumber(int functionNumber) {
			this.functionNumber = functionNumber;
		}

		// Nodes involved in function
		private int[] nodes;

		public int[] getNodes() {
			return nodes;
		}

		public void setNodes(int[] nodes) {
			this.nodes = nodes;
		}

		// Strides for Nodes
		private int[] strides;

		public int[] getStrides() {
			return strides;
		}

		public void setStrides(int[] strides) {
			this.strides = strides;
		}

		// Stride red
		private int[] strideRed;
		
		public int[] getStrideRed() {
			return strideRed;
		}

		public void setStrideRed(int[] strideRed) {
			this.strideRed = strideRed;
		}
		

		// Potentials related to function
		private double[] potentials;

		public double[] getPotentials() {
			return potentials;
		}

		public void setPotentials(double[] potentials) {
			this.potentials = potentials;
		}

		// Number of potentials
		private int numberOfPotentials;

		public int getNumberOfPotentials() {
			return numberOfPotentials;
		}

		public void setNumberOfPotentials(int numberOfPotentials) {
			this.numberOfPotentials = numberOfPotentials;
		}

		// boolean visited
		private boolean visited;

		public boolean isVisited() {
			return visited;
		}

		public void setVisited(boolean visited) {
			this.visited = visited;
		}
		
		private Map<Integer,Integer> domains;

		public Map<Integer,Integer> getDomains() {
			return domains;
		}

		public void setDomains(Map<Integer,Integer> domains) {
			this.domains = domains;
		}
		

	}

	// End of Function Class //
	// *****************************************************************************************************************//

	// *****************************************************************************************************************//
	// Functions //

	/**
	 * set number of variables
	 * @param input
	 */
	public void setNumberOfNodes(String input) {
		setNumberOfNodes(Integer.parseInt(input));
	}

	/**
	 * Variable array size
	 * @param numberOfNodes
	 */
	public void defineNodeArray(int numberOfNodes) {
		nodeArray = new Node[numberOfNodes];
	}

	/**
	 * define variables
	 * @param domainSizes
	 * @param numberOfNodes
	 */
	public void defineNodes(int[] domainSizes, int numberOfNodes) {
		for (int i = 0; i < numberOfNodes; i++) {
			nodeArray[i] = new GraphicalModel.Node();
			nodeArray[i].setNodeNumber(i);
			nodeArray[i].setDomainSize(domainSizes[i]);
			nodeArray[i].adjacentNodes = new HashSet<Integer>();
			nodeArray[i].setAdjacentNodeCount(0);
			nodeArray[i].setFunctions(new int[1000]);
			nodeArray[i].setNumberOfFunctions(0);
		}
	}

	/**
	 * set number of functions
	 * @param input
	 */
	public void setNumberOfFunctions(String input) {
		setNumberOfFunctions(Integer.parseInt(input));
	}

	/**
	 * Define function array size
	 * @param numberOfFunctions
	 */
	public void defineFunctionArray(int numberOfFunctions) {
		functionArray = new Function[numberOfFunctions];
	}

	/**
	 * Define Functions
	 * @param input
	 * @param functionNumber
	 */
	public void defineFunctions(int[] input, int functionNumber) {
		
		int numberOfNodes = input[0];

		functionArray[functionNumber] = new GraphicalModel.Function();

		functionArray[functionNumber].nodes = new int[numberOfNodes];

		functionArray[functionNumber].strides = new int[numberOfNodes];

		functionArray[functionNumber].strideRed=new int[numberOfNodes];
				
		functionArray[functionNumber].functionNumber = functionNumber;
		
		functionArray[functionNumber].domains = new HashMap<Integer,Integer>();
		
		
				
		for (int i = 0; i<numberOfNodes; i++)
		{
			int tempNode = input[i+1];

			functionArray[functionNumber].nodes[i] = tempNode;

			nodeArray[tempNode].functions[nodeArray[tempNode].numberOfFunctions] = functionNumber;

			nodeArray[tempNode].numberOfFunctions++;
			
			functionArray[functionNumber].domains.put(tempNode,nodeArray[tempNode].domainSize);

			for (int j = 0; j < numberOfNodes; j++) {
				if (i != j) {
					nodeArray[tempNode].adjacentNodes.add(input[j + 1]);
					nodeArray[tempNode].adjacentNodeCount++;

				}
			}
		}
		
		int i = numberOfNodes-1;
		functionArray[functionNumber].strides[i--] = 1;
		for (; i>=0; i--)
		{		 
			functionArray[functionNumber].strides[i] = functionArray[functionNumber].strides[i + 1] * nodeArray[functionArray[functionNumber].nodes[i+1]].domainSize;
			
		}
		
				
		for(int n = 0; n < functionArray[functionNumber].strides.length/2; n++)
		{
		    int temp = functionArray[functionNumber].strides[n];
		    functionArray[functionNumber].strides[n] = functionArray[functionNumber].strides[functionArray[functionNumber].strides.length - n - 1];
		    functionArray[functionNumber].strides[functionArray[functionNumber].strides.length - n - 1] = temp;
		}
		

	}

	/**
	 * Set number of potentials
	 * @param input
	 * @param functionNumber
	 */
	public void setNumberOfPotentials(int input, int functionNumber) {
		functionArray[functionNumber].setNumberOfPotentials(input);
		functionArray[functionNumber].potentials = new double[input];
	}

	/**
	 * Add potentials to functions
	 * @param input
	 * @param functionNumber
	 * @param potentialNumber
	 */
	public void addPotentials(double input, int functionNumber,
			int potentialNumber) {

		functionArray[functionNumber].potentials[potentialNumber] = input;

	}

	/**
	 * Reading Evidence
	 * @param evidenceNode
	 * @param evidenceValue
	 */
	public void addEvidence(int evidenceNode,int evidenceValue)
	{
		nodeArray[evidenceNode].evidence=true;
		nodeArray[evidenceNode].evidenceValue=evidenceValue;
	}
	
	/** 
	 * Min Degree calculation
	 * @param graphicalModel
	 * @return
	 */
	
	public GraphicalModel.Node[] minDegree(GraphicalModel graphicalModel) {
		
		int j = 0;

		java.util.PriorityQueue<GraphicalModel.Node> priorityQ = new java.util.PriorityQueue<GraphicalModel.Node>();

		for (int i = 0; i < graphicalModel.numberOfNodes; i++) {
			if(graphicalModel.nodeArray[i].evidence==false)
			{
				priorityQ.add(graphicalModel.nodeArray[i]);
			}
		}
		
		GraphicalModel.Node[] minDegree = new GraphicalModel.Node[graphicalModel.numberOfNodes-graphicalModel.numberOfEvidances];
		
		while(j<minDegree.length)
		{
			minDegree[j] = priorityQ.poll();
			for (int i : minDegree[j].adjacentNodes) 
			{
				graphicalModel.nodeArray[i].adjacentNodes.remove(minDegree[j].nodeNumber);
				graphicalModel.nodeArray[i].adjacentNodeCount--;

				if(graphicalModel.nodeArray[i].evidence==false)
				{
					priorityQ.remove(graphicalModel.nodeArray[i]);
					priorityQ.add(graphicalModel.nodeArray[i]);
				}
			}
			j++;
		}

		return minDegree;
	}

	/**
	 * Evidence Instantiation
	 * @param graphicalModel
	 */
	public void reduce(GraphicalModel graphicalModel)
	{
		for(int i=0;i<graphicalModel.numberOfFunctions;i++)
		{
			GraphicalModel.Function func=new GraphicalModel.Function();
			int evidanceValue;
			int assignment;
			int stride;
			int domainSize;
			
			func=graphicalModel.functionArray[i];
			
			int[] tempNode=new int[func.nodes.length];
			tempNode=func.nodes;
			
			for(int j=0;j<tempNode.length;j++)
			{
						
				if(nodeArray[tempNode[j]].evidence)
				{
					evidanceValue=nodeArray[tempNode[j]].evidenceValue;
					stride=func.strides[func.nodes.length-j-1];
					domainSize=nodeArray[tempNode[j]].domainSize;
					
					for(int k=0;k<func.numberOfPotentials;k++)
					{
						assignment=((k/stride)%domainSize);
						
						if(evidanceValue!=assignment)
						{
							graphicalModel.functionArray[i].potentials[k]=0;
						}
					}
					
				}
			}
		}
	}
	
	
	/**
	 * Union of two functions
	 * @param function1
	 * @param function2
	 * @return
	 */
	private int[] union(GraphicalModel.Function function1, GraphicalModel.Function function2) {
        
        Arrays.sort(function1.nodes);
        Arrays.sort(function2.nodes);

        int[] result = new int[function1.nodes.length + function2.nodes.length];
        int count = 0, i = 0, j = 0;
        
        while (i < function1.nodes.length && j < function2.nodes.length)
        {
            if (function1.nodes[i] < function2.nodes[j])
            {
                result[count++] = function1.nodes[i++];
            }
            else if (function1.nodes[i] > function2.nodes[j]) {
                result[count++] = function2.nodes[j++];
            } 
            else {
                result[count++] = function1.nodes[i++];
                j++;
            }
        }
        while (i < function1.nodes.length) {
            result[count++] = function1.nodes[i++];
        }
        while (j < function2.nodes.length) {
            result[count++] = function2.nodes[j++];
        }
        //Check for need to trim the result
        return Arrays.copyOf(result, count);
    }
	
	
	/**
	 * Function to return stride of function
	 * @param nodeNumber
	 * @param function
	 * @param graphicalModel
	 * @return
	 */
	public int getStride(int nodeNumber, GraphicalModel.Function function,GraphicalModel graphicalModel) {
		int k = 1, j = 0;

		for (int i = 0; i < function.nodes.length; i++)
		{
			j++;
			if (nodeNumber == function.nodes[i]) 
			{
				break;
			}
			k++;
		}

		if (k > function.nodes.length)
		{
			return 0;
		}
		else
		{
			return function.strides[function.nodes.length-j];
		}
	}

	/**
	 * Product of to factors
	 * @param graphicalModel
	 * @param function1
	 * @param function2
	 * @return
	 */
	public GraphicalModel.Function product(GraphicalModel graphicalModel,GraphicalModel.Function function1, GraphicalModel.Function function2)
	{
		GraphicalModel.Function result = new GraphicalModel.Function();

		int j = 0;
		int k = 0;
		int numberOfPotentials = 1;

		int[] resultNodes = union(function1, function2);
		
		Arrays.sort(resultNodes);
				
		for (int m = 0; m < resultNodes.length; m++)
		{
			numberOfPotentials = numberOfPotentials*graphicalModel.nodeArray[resultNodes[m]].domainSize;
		}

		result.nodes = new int[resultNodes.length];
		
		for (int i = 0; i < resultNodes.length; i++)
		{
			result.nodes[i] = resultNodes[i];
		}

		int[] assignment = new int[resultNodes.length];
		
		for (int l = 0; l < resultNodes.length; l++)
		{
			assignment[l] = 0;
		}

		result.numberOfPotentials = numberOfPotentials;
		result.potentials = new double[numberOfPotentials];
		result.strides = new int[resultNodes.length];

		for (int i = 0; i < numberOfPotentials; i++) 
		{
			result.potentials[i] = function1.potentials[j]*function2.potentials[k];

			for (int l = resultNodes.length - 1; l >= 0; l--) 
			{
				assignment[l] = assignment[l] + 1;

				if (assignment[l] == graphicalModel.nodeArray[result.nodes[l]].domainSize)
				{
					assignment[l] = 0;
					j = j - ((graphicalModel.nodeArray[result.nodes[l]].domainSize - 1) * graphicalModel.getStride(result.nodes[l], function1,graphicalModel));
					k = k - ((graphicalModel.nodeArray[result.nodes[l]].domainSize - 1) * graphicalModel.getStride(result.nodes[l], function2,graphicalModel));
				}
				else 
				{
					j = j + graphicalModel.getStride(result.nodes[l],function1, graphicalModel);
					k = k + graphicalModel.getStride(result.nodes[l],function2, graphicalModel);
					break;
				}
			}

		}

		
		result.strides[result.strides.length - 1] = 1;
		for (int i = result.strides.length - 2; i >= 0; i--)
		{
			result.strides[i] = result.strides[i + 1] * nodeArray[result.nodes[i+1]].domainSize;
		}

		
		
		for(int n = 0; n < result.strides.length/2; n++)
		{
		    int temp2 = result.strides[n];
		    result.strides[n] = result.strides[result.strides.length - n - 1];
		    result.strides[result.strides.length - n - 1] = temp2;
		}
		
		
		return result;
	}

	/**
	 * Summing up a variable in elimination order
	 * @param graphicalModel
	 * @param function
	 * @param nodeNumber
	 * @return
	 */
	public GraphicalModel.Function sumOutVariable(GraphicalModel graphicalModel, GraphicalModel.Function function,int nodeNumber) 
	{
		GraphicalModel.Node node = new GraphicalModel.Node();
		node = graphicalModel.nodeArray[nodeNumber];

		GraphicalModel.Function result = new GraphicalModel.Function();
		graphicalModel.numberOfFunctions++;

		result.functionNumber = graphicalModel.numberOfFunctions - 1;
		result.nodes = new int[function.nodes.length - 1];

		result.numberOfPotentials = 1;
		for (int m = 0; m < function.nodes.length; m++) {
			if (function.nodes[m] != nodeNumber) 
			{
				result.numberOfPotentials = result.numberOfPotentials* graphicalModel.nodeArray[function.nodes[m]].domainSize;
			}
		}

		result.potentials = new double[result.numberOfPotentials];
		result.strides = new int[result.nodes.length];

		boolean[] visited = new boolean[function.numberOfPotentials];
		int strideNode = 0;
		int domainSizeNode = node.domainSize;
		int j = 0;
		int k = 0;

		for (int i = 0; i < function.nodes.length; i++) {
			if (function.nodes[i] == node.nodeNumber)
			{
				strideNode = function.strides[function.nodes.length-1-i];
			}
			else
			{
				graphicalModel.nodeArray[function.nodes[i]].functions[graphicalModel.nodeArray[function.nodes[i]].numberOfFunctions] = result.functionNumber;
				graphicalModel.nodeArray[function.nodes[i]].numberOfFunctions++;

				result.nodes[j] = function.nodes[i];
				
				j++;
			}
		}
		
		for(int i=result.strides.length-1;i>=0;i--)
		{
			if (i == result.strides.length-1) 
			{
				result.strides[i] = 1;
			} 
			else 
			{
				result.strides[i] = result.strides[i+1]* nodeArray[result.nodes[i+1]].domainSize;
			}
		}

		for(int n = 0; n < result.strides.length/2; n++)
		{
		    int temp2 = result.strides[n];
		    result.strides[n] = result.strides[result.strides.length - n - 1];
		    result.strides[result.strides.length - n - 1] = temp2;
		}
		
		for (int i = 0; i < function.numberOfPotentials; i++)
		{
			if (visited[i] == false) {
				for (int l = 0; l < domainSizeNode; l++) 
				{
					result.potentials[k] += function.potentials[i+ (l * strideNode)];
					visited[i + l * strideNode] = true;
				}
				k++;
			}
		}

		if (graphicalModel.numberOfFunctions == graphicalModel.functionArray.length + 1) {
			GraphicalModel.Function[] temp = new GraphicalModel.Function[graphicalModel.functionArray.length];
			temp = graphicalModel.functionArray;
			graphicalModel.functionArray = new GraphicalModel.Function[temp.length + 1];
			for (int i = 0; i < temp.length; i++) {
				graphicalModel.functionArray[i] = temp[i];
			}
		}
		graphicalModel.functionArray[graphicalModel.functionArray.length - 1] = result;

		return result;
	}

	
	/**
	 * Evidence Instantiation support function
	 * @param graphicalModel
	 * @param function
	 * @param nodeNumber
	 */
		public void sumOutVariablered(GraphicalModel graphicalModel, GraphicalModel.Function function,int nodeNumber) 
		{
			GraphicalModel.Node node = new GraphicalModel.Node();
			node = graphicalModel.nodeArray[nodeNumber];

			GraphicalModel.Function result = new GraphicalModel.Function();

			result.functionNumber = function.functionNumber;
			result.nodes = new int[function.nodes.length - 1];

			result.numberOfPotentials = 1;
			
			for (int m = 0; m < function.nodes.length; m++) {
				if (function.nodes[m] != nodeNumber) 
				{
					result.numberOfPotentials = result.numberOfPotentials* graphicalModel.nodeArray[function.nodes[m]].domainSize;
				}
			}

			result.potentials = new double[result.numberOfPotentials];
			result.strides = new int[result.nodes.length];

			boolean[] visited = new boolean[function.numberOfPotentials];
			int strideNode = 0;
			int domainSizeNode = node.domainSize;
			int j = 0;
			int k = 0;

			for (int i = 0; i < function.nodes.length; i++) {
				if (function.nodes[i] == node.nodeNumber)
				{
					strideNode = function.strides[function.nodes.length-1-i];
				}
				else
				{
					graphicalModel.nodeArray[function.nodes[i]].functions[graphicalModel.nodeArray[function.nodes[i]].numberOfFunctions] = result.functionNumber;
					
					result.nodes[j] = function.nodes[i];
					
					j++;
				}
			}
			
			
			for(int i=result.strides.length-1;i>=0;i--)
			{
				if (i == result.strides.length-1) 
				{
					result.strides[i] = 1;
				} 
				else 
				{
					result.strides[i] = result.strides[i+1]* nodeArray[result.nodes[i+1]].domainSize;
				}
			}
			
			for(int n = 0; n < result.strides.length/2; n++)
			{
			    int temp2 = result.strides[n];
			    result.strides[n] = result.strides[result.strides.length - n - 1];
			    result.strides[result.strides.length - n - 1] = temp2;
			}

			for (int i = 0; i < function.numberOfPotentials; i++)
			{
				if (visited[i] == false) {
					for (int l = 0; l < domainSizeNode; l++) 
					{
						result.potentials[k] += function.potentials[i+ (l * strideNode)];
						visited[i + l * strideNode] = true;
					}
					k++;
				}
			}

			
			
			graphicalModel.functionArray[function.functionNumber] = result;

		}
		
		
		/**
		 * Evidence Instantiation
		 * @param graphicalModel
		 */
		
		public void reduce2(GraphicalModel graphicalModel)
		{
			for(int i=0;i<graphicalModel.numberOfFunctions;i++)
			{
				GraphicalModel.Function func=new GraphicalModel.Function();
								
				func=graphicalModel.functionArray[i];
				
				int[] tempNode=new int[func.nodes.length];
				tempNode=func.nodes;
				
				for(int j=0;j<tempNode.length;j++)
				{
					if(nodeArray[tempNode[j]].evidence)
					{
						func=graphicalModel.functionArray[i];
						graphicalModel.sumOutVariablered(graphicalModel, func, tempNode[j]);
						
					}
				}
			}
		}
		
	
		
	/**
	 * Bucket Elimination
	 * @param graphicalModel
	 * @return
	 */
	public GraphicalModel.Function bucketElimination(GraphicalModel graphicalModel) 
	{
		GraphicalModel.Node[] minDegree = new GraphicalModel.Node[graphicalModel.numberOfNodes];
		GraphicalModel.Function result = new GraphicalModel.Function();

		minDegree = graphicalModel.minDegree(graphicalModel);

		if(graphicalModel.numberOfEvidances>0)
		{
			graphicalModel.reduce(graphicalModel);
			graphicalModel.reduce2(graphicalModel);
		}
     		for (int i = 0; i < minDegree.length; i++)
     		{
     			GraphicalModel.Node temp = new GraphicalModel.Node();
     			GraphicalModel.Function tempFunc = new GraphicalModel.Function();
     			int l = 0;

     			temp = minDegree[i];

     			for (int j = 0; j < graphicalModel.nodeArray[temp.nodeNumber].numberOfFunctions; j++) 
     			{
     				if (graphicalModel.functionArray[graphicalModel.nodeArray[temp.nodeNumber].functions[j]].visited == false)
     				{
     					l++;
     					tempFunc = graphicalModel.functionArray[graphicalModel.nodeArray[temp.nodeNumber].functions[j]];
     					graphicalModel.functionArray[graphicalModel.nodeArray[temp.nodeNumber].functions[j]].visited = true;
     					break;
     				}
     			}

     			for (int j = l; j < graphicalModel.nodeArray[temp.nodeNumber].numberOfFunctions; j++) 
     			{
     				if (graphicalModel.functionArray[graphicalModel.nodeArray[temp.nodeNumber].functions[j]].visited == false)
     				{
     					tempFunc = graphicalModel.product(graphicalModel,tempFunc,graphicalModel.functionArray[graphicalModel.nodeArray[temp.nodeNumber].functions[j]]);
     					graphicalModel.functionArray[graphicalModel.nodeArray[temp.nodeNumber].functions[j]].visited = true;
     				}
     			}

     			result = graphicalModel.sumOutVariable(graphicalModel, tempFunc,temp.nodeNumber);
     		}

		return result;
	}

	/************************************************************************************************/
	 
	 
	
	/**
	 * Main Funtion
	 * @param args
	 * @throws IOException
	 */
	
	public static void main(String args[]) throws IOException {
		GraphicalModel graphicalModel = new GraphicalModel();
        GraphicalModel.Function func=graphicalModel.new Function();
        double prob=1;
        
		String variableFile = args[0];
		String evidanceFile = args[1];
		
		Scanner scan = new Scanner(new File(variableFile));
		Scanner scan2 = new Scanner(new File(evidanceFile));
		
		// Reading network type
		String networkType = scan.nextLine();
		graphicalModel.setModelType(networkType);

		// Reading number of nodes
		int numberOfNodes = scan.nextInt();
		graphicalModel.setNumberOfNodes(numberOfNodes);
		graphicalModel.defineNodeArray(numberOfNodes);

		// Reading domain sizes and initializing each node
		int[] domainSizes = new int[numberOfNodes];
		for (int i = 0; i < numberOfNodes; i++) {
			domainSizes[i] = scan.nextInt();
		}
		graphicalModel.defineNodes(domainSizes, numberOfNodes);

		// Reading number of functions
		int numberOfFunctions = scan.nextInt();
		graphicalModel.setNumberOfFunctions(numberOfFunctions);
		graphicalModel.defineFunctionArray(numberOfFunctions);

		// Define Functions
		for (int i = 0; i < numberOfFunctions; i++) {
			int nodes = scan.nextInt();
			int[] function = new int[nodes + 1];
			function[0] = nodes;
			for (int j = 1; j < nodes + 1; j++) {
				function[j] = scan.nextInt();
			}

			graphicalModel.defineFunctions(function, i);
		}

		// set number of potentials and add potentials to functions
		for (int i = 0; i < numberOfFunctions; i++) {
			int numberOfPotentials = scan.nextInt();
			graphicalModel.setNumberOfPotentials(numberOfPotentials, i);
			for (int j = 0; j < numberOfPotentials; j++) {
				double potential = scan.nextDouble();
				graphicalModel.addPotentials(potential, i, j);
			}
		}

		// Set evidence
		
		int numberOfEvidences=scan2.nextInt();
		graphicalModel.setNumberOfEvidances(numberOfEvidences);
		if(numberOfEvidences!=0)
		{
			for(int i=0;i<numberOfEvidences;i++)
			{
				int evidenceNode=scan2.nextInt();
				int evidenceValue=scan2.nextInt();
				graphicalModel.addEvidence(evidenceNode, evidenceValue);
			}
		}
		
		
		func=graphicalModel.bucketElimination(graphicalModel);
		
		System.out.println("Shravan Chinta - SXC129530@utdallas.edu");
		System.out.println("_______________________________________");
		
		for(int i=0;i<graphicalModel.functionArray.length;i++)
		{
			if(!graphicalModel.functionArray[i].visited)
			{
				prob=prob*graphicalModel.functionArray[i].potentials[0];
			}
		}
		System.out.println("Reading "+graphicalModel.modelType+" Network");
		System.out.println("Number of Variables "+graphicalModel.numberOfNodes);
		System.out.println("Number of functions "+graphicalModel.numberOfFunctions);
		System.out.println("Number of Evidances "+graphicalModel.numberOfEvidances);
		System.out.println("______________________________________________________");
		System.out.println("Probability of Evidance is "+prob);
		
	}
}
