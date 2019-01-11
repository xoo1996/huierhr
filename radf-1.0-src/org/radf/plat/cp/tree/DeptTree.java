package org.radf.plat.cp.tree;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.radf.manage.department.dto.DeptDTO;
import org.radf.plat.commons.ClassHelper;
import org.radf.plat.cp.a.c;
import org.radf.plat.util.global.GlobalNames;

public class DeptTree extends TreeBuilder {
	private Trees _fldbyte;

	private Collection _fldtry;

	public DeptTree(Collection collection) {
		_fldbyte = null;
		_fldtry = null;
		_fldtry = collection;
	}

	public void BuildTree() {
		_fldbyte = new Trees("deptTree");
		_fldbyte.setTreeType(GlobalNames.COMMON_TREE);
		try {
			ArrayList list = (ArrayList) _fldtry;
			Tree tree = _fldbyte.newTree();
			tree.addLabel("机构管理");
			tree.addTitle("00");
			for (int i = 0; i < list.size(); i++) {
				DeptDTO dto = new DeptDTO();
				ClassHelper.copyProperties(list.get(i), dto);
				if (dto.getBsc999() == null||dto.getBsc999().equals("G_")) {
					String bsc001 = dto.getAab003();
					Tree tree1 = tree.newTree();
					tree1.addLabel(dto.getAab300());
					tree1.addTitle(bsc001);
					a(tree1, bsc001);
				}
			}

		} catch (Exception exception) {
			exception.printStackTrace();
		}
	}

	private void a(Tree tree, String bsc001) {
		List list = (List) c.a1(_fldtry, bsc001);
		if (list != null) {
			String aab003 = "";
			for (int i = 0; i < list.size(); i++) {
				DeptDTO dto = (DeptDTO) list.get(i);
				String long2 = dto.getAab003();
				if (c.a1(_fldtry, long2) != null) {
					Tree tree1 = tree.newTree();
					tree1.addLabel(dto.getAab300());
					tree1.addTitle(long2);
					a(tree1, long2);
				} else {
					TreeNode treenode = tree.newTreeNode();
					if("G".equals(long2.subSequence(0,1)))
					{
						treenode.addLabel("(机构)"+dto.getAab300());
					}else
					{
						if("G_0101".equals(dto.getBsc999()))
						{
							treenode.addLabel("(科室)"+dto.getAab300());
						}else
						{
							treenode.addLabel("(直属店)"+dto.getAab300());
						}
					}
					
					treenode.addTitle(long2);
				}
			}
		}
	}

	public Object getTree() {
		return _fldbyte;
	}

	public Collection getMainMenu() {
		return null;
	}
}
