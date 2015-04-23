package pset3;

import org.apache.bcel.Repository;
import org.apache.bcel.classfile.JavaClass;
import org.apache.bcel.classfile.Method;
import org.apache.bcel.generic.*;

import java.util.HashMap;

//import org.apache.bcel.classfile.*;
//import org.apache.bcel.generic.*;

/**
 * Created by kgowru on 3/26/15.
 */
public class GraphGenerator {

    //EXIT or RETURN STATEMENT INDEX
    public static final int EXIT_STATEMENT = -1;

    public CFG createCFG(String className) throws ClassNotFoundException {
        CFG cfg = new CFG();

        JavaClass jc = Repository.lookupClass(className);
        ClassGen cg = new ClassGen(jc);
        ConstantPoolGen cpg = cg.getConstantPool();
        for (Method m: cg.getMethods()) {
            MethodGen mg = new MethodGen(m, cg.getClassName(), cpg);
            InstructionList il = mg.getInstructionList();
            InstructionHandle[] handles = il.getInstructionHandles();
            for (InstructionHandle ih: handles) {
                int position = ih.getPosition();
                cfg.addNode(position, m, jc);
                Instruction inst = ih.getInstruction();

                try {
                    //exclude switch or jsr[_w] statements
                    if (inst.getOpcode() == 201 || inst.getOpcode() == 171 || inst.getOpcode() == 170 || inst.getOpcode() == 168)
                        throw new Exception();
                    if(inst instanceof ReturnInstruction) {
                        cfg.addEdge(position, this.EXIT_STATEMENT, m, jc);
                    } else if (inst instanceof BranchInstruction) {
                        int nextPosition = ih.getNext() == null ? this.EXIT_STATEMENT : ih.getNext().getPosition();
                        cfg.addEdge(position, nextPosition, m, jc);
                        int targetPosition = ((BranchInstruction) inst).getTarget().getPosition();
                        cfg.addEdge(position, targetPosition, m, jc);
                    } else throw new Exception();
                } catch (Exception e){
                    int nextPosition = ih.getNext() == null ? this.EXIT_STATEMENT : ih.getNext().getPosition();
                    cfg.addEdge(position, nextPosition, m, jc);
                }
            }
        }
        return cfg; }
    public CFG createCFGWithMethodInvocation(String className) throws ClassNotFoundException {
        CFG cfg = new CFG();

        JavaClass jc = Repository.lookupClass(className);
        ClassGen cg = new ClassGen(jc);
        ConstantPoolGen cpg = cg.getConstantPool();
        HashMap<Integer, MethodGen> methodsMap = new HashMap<Integer, MethodGen>();

        //create map of methods for use later
        int size = 0;
        for (Method m: cg.getMethods()){
            methodsMap.put(size, new MethodGen(m, cg.getClassName(), cpg));
            size++;
        }

        for (Method m : cg.getMethods()) {
            MethodGen mg = new MethodGen(m, cg.getClassName(), cpg);
            InstructionList il = mg.getInstructionList();
            InstructionHandle[] handles = il.getInstructionHandles();
            for (InstructionHandle ih : handles) {
                int position = ih.getPosition();
                cfg.addNode(position, m, jc);
                Instruction inst = ih.getInstruction();

                try {
                    //exclude switch or jsr[_w] statements
                    if (inst.getOpcode() == 201 || inst.getOpcode() == 171 || inst.getOpcode() == 170 || inst.getOpcode() == 168) {
                        throw new Exception();
                    }

                    if (inst instanceof ReturnInstruction) {
                        cfg.addEdge(position, this.EXIT_STATEMENT, m, jc);
                    } else if (inst instanceof BranchInstruction) {
                        int nextPosition = ih.getNext() == null ? this.EXIT_STATEMENT : ih.getNext().getPosition();
                        cfg.addEdge(position, nextPosition, m, jc);
                        int targetPosition = ((BranchInstruction) inst).getTarget().getPosition();
                        cfg.addEdge(position, targetPosition, m, jc);
                    } else if (inst instanceof INVOKESTATIC) {
                        String [] verboseOutput = inst.toString().split("\\s");
                        int invocationTarget = Integer.parseInt(verboseOutput[verboseOutput.length - 1]);
                        int nextPosition = 0;
                        cfg.addEdge(position, m, jc, nextPosition, methodsMap.get(invocationTarget).getMethod(), jc);
                        cfg.addEdge(position = this.EXIT_STATEMENT, methodsMap.get(invocationTarget).getMethod(), jc, nextPosition = ih.getNext().getPosition(), m, jc);
                    } else throw new Exception();
                } catch (Exception e) {
                    int nextPosition = ih.getNext() == null ? this.EXIT_STATEMENT : ih.getNext().getPosition();
                    cfg.addEdge(position, nextPosition, m, jc);
                }
            }
        }
        return cfg;
    }
    public static void main(String[] a) throws ClassNotFoundException {
        GraphGenerator gg = new GraphGenerator();
        CFG cfg = gg.createCFG("pset3.C");// example invocation of createCFG
        System.out.println(cfg.toString());
        CFG methodCfg = gg.createCFGWithMethodInvocation("pset3.D"); // example invocation of createCFGWithMethodInovcation
        System.out.println(methodCfg.toString());
        String fromMethod = "main";
        String toMethod = "main";
        String className = "pset3.D";
        System.out.println("Is \'" + toMethod + "\' reachable from \'" + fromMethod +  "\' in \'" + className +"\'? " + methodCfg.isReachable(fromMethod, className, toMethod, className));
    }
}